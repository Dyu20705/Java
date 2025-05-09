package Utils;

import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ExceptionHandler {
    private static final Logger logger = Logger.getLogger(ExceptionHandler.class.getName());

    // Khởi tạo logger
    static {
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    /**
     * Xử lý ngoại lệ chung
     * @param e Ngoại lệ cần xử lý
     * @param message Thông báo tùy chỉnh liên quan đến lỗi
     */
    public static void handleException(Exception e, String message) {
        // Ghi log lỗi với mức độ SEVERE
        logger.log(Level.SEVERE, message, e);
        
        // Hiển thị thông báo cho người dùng
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message + "\nChi tiết lỗi: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
    }

    /**
     * Xử lý ngoại lệ cụ thể cho SQL
     * @param e SQLException cần xử lý
     * @param message Thông báo tùy chỉnh
     */
    public static void handleSQLException(SQLException e, String message) {
        logger.log(Level.SEVERE, message + " - SQL State: " + e.getSQLState(), e);
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, 
                message + "\nMã lỗi SQL: " + e.getErrorCode(), 
                "Lỗi Cơ Sở Dữ Liệu", JOptionPane.ERROR_MESSAGE);
        });
    }

    /**
     * Thiết lập xử lý ngoại lệ không bắt được cho các luồng
     */
    public static void setupGlobalExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> {
            handleException((Exception) e, 
                "Lỗi không bắt được trong luồng: " + thread.getName());
        });
    }
}
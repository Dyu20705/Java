package BanTau;

import java.util.Scanner;

public class Main {
	private static int soTau = 50;
	private static int lanBan = 70;
	private static final int SIZE = 10; 
	private static final int coTau = -1; 
	private static final int khongCoTau = -2;
	private static final int daBanVao = -3;
	
	public static void show(int game[][]) {
		System.out.println("**Game ban tau**\n");
		System.out.println("So tau con lai la: " + soTau);
		System.out.println("So lan ban con lai la: " + lanBan);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE;j++) {
				if(game[i][j] == coTau || game[i][j] == khongCoTau) {
					System.out.print("*\t");
				} else {
					System.out.print(game[i][j] + "\t");
				}
			}
			System.out.println("");
		}
	}
	
	public static int count(int game[][], int x, int y) {
        int demTau = 0;
        
        int xLow = x > 0? x - 1 : 0;
        int xUp = x < 9? x + 1 : SIZE - 1;
        
        int yLow = y > 0? y - 1 : 0;
        int yUp = y < 9? y + 1 : SIZE - 1;
        
        for (int i = xLow; i <= xUp; i++) {
            for (int j = yLow; j <= yUp; j++) {
                if (game[i][j] == coTau) {
                    demTau++;
                }
            }
        }
        return demTau;
    }
	
	public static void shoot(int game[][], int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            System.out.println("Toạ độ không hợp lệ!");
            return;
        }
        if (game[x][y] == daBanVao) {
            System.out.println("Ô này đã bị bắn rồi!");
            return;
        }
        if (game[x][y] == coTau) {
            game[x][y] = daBanVao;
            soTau--;
            System.out.println("Bạn đã bắn trúng tàu!");
        } else if (game[x][y] == khongCoTau) {
            game[x][y] = count(game, x, y);
        }
        lanBan--;
    }
	
	public static int getStatus() {
		if(soTau == 0) {
			return 1;
		}
		else if(lanBan == 0) {
			return -1;
		}
		return 0;
	}
	
	public static void play(int game[][]) {
		Scanner scanner = new Scanner(System.in);
        while (true) {
        	//Step 1: In ra board
            show(game);
            
            //Step 2: Nhap toa do
            System.out.print("Nhập toạ độ x, y: ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            
            //Step 3: Thuc hien ban
            shoot(game, x, y);
            
            //Step 4: Thuc hien check
            int status = getStatus();
            if (status == 1) {
                System.out.println("Chúc mừng! Bạn đã thắng!");
                break;
            } else if (status == -1) {
                System.out.println("Hết lượt! Bạn đã thua.");
                break;
            }
        }
        scanner.close();
	}
	
	
	public static void main(String[] args) {
		int game[][] = {
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},	
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
				{-2, -1, -2, -1, -2, -1, -2, -1, -2, -1},
		};
        if (soTau > lanBan) {
            System.out.println("Không thể thực hiện trò chơi!");
        } else {
            play(game);
        }
	}
}


    

    

    

    

   


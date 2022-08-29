import java.util.Random;

public class Car implements Runnable{
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // Khởi tạo điểm start(hay km ban đầu)
        int runDistance = 0;
        // Khởi tạo time bắt đầu cuộc đua
        long startTime = System.currentTimeMillis();

        // Kiểm tra chừng nào còn xe chưa kết thúc quãng đường đua thì xe vẫn tiếp tục chạy
        while (runDistance < Main.DISTANCE) {
            try {
                // Random tốc độ KM/H
                int speed = (new Random()).nextInt(20);
                // Tính quãng đường đã đi
                runDistance += speed;
                // Xây dựng biểu đồ kết quả
                String log = "|";
                int percentTravel = (runDistance * 100) / Main.DISTANCE;
                for (int i = 0; i < Main.DISTANCE; i += Main.STEP) {
                    if (percentTravel >= i + Main.STEP) {
                        log += "=";
                    } else if (percentTravel >= i && percentTravel < i + Main.STEP) {
                        log += "o";
                    } else {
                        log += "-";
                    }
                }
                log += "|";
                System.out.println("Xe" + this.name + ": " + log + " " + Math.min(Main.DISTANCE, runDistance) + "KM");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Xe" + this.name + " Bi hong...");
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Xe" + this.name + " Ket thuc trong " + (endTime - startTime) / 1000 + "s");
    }
}

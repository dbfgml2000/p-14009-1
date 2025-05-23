package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingApp {
    
    Scanner scanner = new Scanner(System.in);
    int lastId = 0;
    List<WiseSaying> list = new ArrayList<>();
    
    public void run() {
        System.out.println("== 명언 앱 ==");
        
        label:
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            int targetId = -1;
            if(cmd.startsWith("삭제?id=") || cmd.startsWith("수정?id=")) {
                targetId = Integer.parseInt(cmd.substring(6));
                cmd = cmd.substring(0, 2);
            }

            switch (cmd) {
                case "종료":
                    break label;
                case "등록":
                    register();
                    break;
                case "목록":
                    printList();
                    break;
                case "삭제": {
                    delete(targetId);
                    break;
                }
                case "수정": {
                    update(targetId);
                    break;
                }
            }
        }
    }

    private void update(int targetId) {
        for (WiseSaying wiseSaying : list) {
            if (wiseSaying.getId() == targetId) {

                System.out.println("명언(기존) : " + wiseSaying.getContent());
                System.out.print("명언 : ");
                String newContent = scanner.nextLine().trim();

                System.out.println("작가(기존) : " + wiseSaying.getAuthor());
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine().trim();

                wiseSaying.setContent(newContent);
                wiseSaying.setAuthor(newAuthor);

                return;
            }
        }
        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
    }

    private void delete(int targetId) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == targetId) {
                list.remove(i);
                System.out.println(targetId + "번 명언이 삭제되었습니다.");
                return;
            }
        }
        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
    }

    private void printList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        if(list.isEmpty()){
            return;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            list.get(i).printInfo();
        }
    }

    private void register() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        list.add(new WiseSaying(++lastId, content, author));
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

}

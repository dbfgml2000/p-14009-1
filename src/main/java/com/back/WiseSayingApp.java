package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingApp {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        int lastId = 0;
        List<WiseSaying> list = new ArrayList<>();


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
                    System.out.print("명언 : ");
                    String wiseSayingContent = scanner.nextLine().trim();
                    System.out.print("작가 : ");
                    String wiseSayingAuthor = scanner.nextLine().trim();

                    list.add(new WiseSaying(++lastId, wiseSayingContent, wiseSayingAuthor));
                    System.out.println(lastId + "번 명언이 등록되었습니다.");
                    break;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");
                    for (int i = list.size() - 1; i >= 0; i--) {
                        list.get(i).printInfo();
                    }
                    break;
                case "삭제": {
                    boolean find = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() == targetId) {
                            list.remove(i);
                            System.out.println(targetId + "번 명언이 삭제되었습니다.");
                            find = true;
                            break;
                        }
                    }
                    if (!find) {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    }
                    break;
                }
                case "수정": {
                    boolean find = false;
                    for (WiseSaying wiseSaying : list) {
                        if (wiseSaying.getId() == targetId) {
                            System.out.println("명언(기존) : " + wiseSaying.getContent());
                            System.out.print("명언 : ");
                            String newContent = scanner.nextLine().trim();
                            wiseSaying.setContent(newContent);

                            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
                            System.out.print("작가 : ");
                            String newAuthor = scanner.nextLine().trim();
                            wiseSaying.setAuthor(newAuthor);

                            find = true;
                            break;
                        }
                    }
                    if (!find) {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    }
                    break;
                }
            }
        }
    }

}

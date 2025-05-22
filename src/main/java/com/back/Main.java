package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        int lastId = 0;
        List<WiseSaying> list = new ArrayList<>();


        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if(cmd.equals("종료")){
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();
                lastId++;

                list.add(new WiseSaying(lastId, wiseSayingContent, wiseSayingAuthor));
                System.out.println(lastId + "번 명언이 등록되었습니다.");
            } else if(cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i=list.size()-1; i>=0; i--){
                    list.get(i).printInfo();
                }
            } else if(cmd.matches("^삭제\\?id=[0-9]+")){
                int findId = Integer.parseInt(cmd.substring(6));
                boolean find = false;
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).getId() == findId) {
                        list.remove(i);
                        System.out.println(findId + "번 명언이 삭제되었습니다.");
                        find = true;
                        break;
                    }
                }
                if(!find){
                    System.out.println(findId + "번 명언은 존재하지 않습니다.");
                }
            } else if(cmd.matches("^수정\\?id=[0-9]+")) {
                int findId = Integer.parseInt(cmd.substring(6));
                boolean find = false;
                for(int i=0; i<list.size(); i++){
                    WiseSaying wiseSaying = list.get(i);
                    if(wiseSaying.getId() == findId) {
                        System.out.println("명언(기존) : " + wiseSaying.getContent());
                        System.out.print("명언 : ");
                        String wiseSayingContent = scanner.nextLine().trim();
                        wiseSaying.setContent(wiseSayingContent);

                        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
                        System.out.print("작가 : ");
                        String wiseSayingAuthor = scanner.nextLine().trim();
                        wiseSaying.setAuthor(wiseSayingAuthor);

                        find = true;
                        break;
                    }
                }
                if(!find){
                    System.out.println(findId + "번 명언은 존재하지 않습니다.");
                }
            }
        }
    }

    public static class WiseSaying {
        private int id;
        private String content;
        private String author;

        public WiseSaying(int id, String content, String author) {
            this.id = id;
            this.content = content;
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void printInfo() {
            System.out.println(id + " / " + author + " / " + content);
        }
    }
}

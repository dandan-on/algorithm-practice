package p1991;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 이진 트리의 노드 개수 입력
//        if (!sc.hasNextInt()) {
//            System.out.println("입력 형식 오류: 첫 줄에 정수가 필요합니다.");
//            return;
//        }
        int N = sc.nextInt(); // 1 ~ 26
        sc.nextLine(); // 개행 문자 제거
//        if (N <= 0) {
//            System.out.println("입력 형식 오류: 노드 개수 N은 양수여야 합니다.");
//            return;
//        }
        // 각 노드의 자기 자신 / 왼쪽 자식 / 오른쪽 자식 정보 입력
        HashMap<String, Node> treeMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
//            if (!sc.hasNextLine()) {
//                System.out.println("입력 줄이 부족합니다. 기대한 줄 수: " + N);
//                return;
//            }
            String str = sc.nextLine().trim();
//            if (str.isEmpty()) {
//                System.out.println("빈 줄이 입력되었습니다. (line " + (i + 2) + ")");
//                return;
//            }

            String[] children = str.split(" ");
            if (children.length != 3) {
//                System.out.println("입력 형식 오류 (line " + (i + 2) + "): " + str);
                continue;
            }
            String currentKey = children[0];
            String leftKey = children[1];
            String rightKey = children[2];

//            if (!isValidKey(currentKey) || !isValidKey(leftKey) || !isValidKey(rightKey)) {
//                System.out.println("잘못된 노드 이름이 있습니다: " + str);
//                return;
//            }

            Node currentNode = null;
            Node leftNode = null;
            Node rightNode = null;
            if (!Objects.equals(leftKey, ".")) {
                if (treeMap.containsKey(leftKey)) {
                    leftNode = treeMap.get(leftKey);
                } else {
                    leftNode = new Node(leftKey, null, null);
                    treeMap.put(leftKey, leftNode);
                }
            }
            if (!Objects.equals(rightKey, ".")) {
                if (treeMap.containsKey(rightKey)) {
                    rightNode = treeMap.get(rightKey);
                } else {
                    rightNode = new Node(rightKey, null, null);
                    treeMap.put(rightKey, rightNode);
                }
            }
            if (treeMap.containsKey(currentKey)) {
                currentNode = treeMap.get(currentKey);
                currentNode.left = leftNode;
                currentNode.right = rightNode;
            } else {
                treeMap.put(currentKey, new Node(currentKey, leftNode, rightNode));
            }
        }

        // 전위 순회 결과 출력
        Node root = treeMap.get("A");
//        if (root == null) {
//            System.out.println("루트 노드를 찾을 수 없습니다.");
//            return;
//        }
        System.out.println(preOrder(treeMap, root));
        System.out.println(inOrder(treeMap, root));
        System.out.println(postOrder(treeMap, root));
    }

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static String preOrder(HashMap<String, Node> treeMap, Node currentNode) {
        if (currentNode == null) {
            return "";
        }
        String result = currentNode.value;
        result += preOrder(treeMap, currentNode.left);
        result += preOrder(treeMap, currentNode.right);
        return result;
    }

    private static String inOrder(HashMap<String, Node> treeMap, Node currentNode) {
        if (currentNode == null) {
            return "";
        }
        String result = "";
        result += inOrder(treeMap, currentNode.left);
        result += currentNode.value;
        result += inOrder(treeMap, currentNode.right);
        return result;
    }

    private static String postOrder(HashMap<String, Node> treeMap, Node currentNode) {
        if (currentNode == null) {
            return "";
        }
        String result = "";
        result += postOrder(treeMap, currentNode.left);
        result += postOrder(treeMap, currentNode.right);
        result += currentNode.value;
        return result;
    }

    private static boolean isValidKey(String key) {
        if (key.equals(".")) return true;
        return key.length() == 1 && key.charAt(0) >= 'A' && key.charAt(0) <= 'Z';
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.random();
        //graph.add();

        System.out.println("The column length is " + graph.getCol());
        System.out.println("The row length is " + graph.getRow());

        graph.view();

        graph.solve();
        /*
        graph.reset();
        for(int i = 0; i < graph.getRow(); i++) {
            graph.add(0, i);
            graph.add(2, i);
            graph. add (4, i);
        }

        graph.add(1, 0); graph.add(3, 4);
        graph.entrance(0,4);
        graph.view();
        graph.solve();
        */

    }
}
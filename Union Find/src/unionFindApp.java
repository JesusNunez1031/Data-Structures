public class unionFindApp {
    public static void main(String[] args) throws IllegalAccessException {
        unionFind graph = new unionFind(10);
        System.out.println("Original Graph");
        graph.display();
        //number of components should be the size of the union find data structure since all roots are pointing to self
        System.out.println("Number of components: " + graph.components() + "\n");

        //combine all the even numbered components with 0 and odd numbered with 1
        for (int i = 2; i < graph.size(); i++) {
            if (i % 2 == 0) {
                graph.unify(0, i);
            } else {
                graph.unify(1, i);
            }
        }
        System.out.println("Updated Graph");
        graph.display();
        /*
            since we merged all the even numbered components under 0 and odds under 1, we are left with 2 components
            each of size graph.size / 2
         */
        System.out.println("Number of components: " + graph.components());
    }
}

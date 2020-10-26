public class PositionalListDriver {
    public static void main(String[] args) {
        PositionalList<Integer> list = new LinkedPositionalList<>();

        list.addFirst(1);
        list.addAfter(list.first(), 2);

        list.addAfter(list.first(), 3);
        list.remove(list.first());

        //iterate through the positions list
        Position<Integer> iter = list.first();      //set the pointer to the first position in list
        while (iter != null) {
            System.out.println(iter.getElement());
            //set iter to the position after the first
            iter = list.after(iter);
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        ListNode l = new ListNode(4);
        l.next = new ListNode(2);
        l.next.next = new ListNode(1);
        l.next.next.next = new ListNode(3);
        ListNode rs = sortList(l);
        System.out.println(rs.val);
    }

    public static ListNode sortList(ListNode head) {
        ListNode rs = head;
        int n=0;
        while(head!=null){
            n++;
            head=head.next;
        }
        while(n>1){
           rs= helper(rs,n--);
        }
        return rs;
    }

    public static ListNode helper(ListNode head,int n) {
        ListNode rs = head;
        while (n>1) {
            if (head != null && head.next != null) {
                if (head.val > head.next.val) {
                    int tmp = head.val;
                    head.val = head.next.val;
                    head.next.val = tmp;
                }
            }
            head = head.next;
            n--;
            //System.out.println("111");
        }
        //if(rs!=null&&rs.next!=null)rs.next=sortList(rs.next);
        return rs;
    }

}

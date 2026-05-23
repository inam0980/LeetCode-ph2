/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pointer = dummy;
        
        while (pointer != null) {
            // 1. Check if there are at least k nodes left ahead
            ListNode tracker = pointer;
            for (int i = 0; i < k && tracker != null; i++) {
                tracker = tracker.next;
            }
            // If fewer than k nodes remain, we are done
            if (tracker == null) break;
            
            // 2. Clear boundaries for reversal
            ListNode curr = pointer.next;
            ListNode nxt = curr.next;
            
            // 3. Reverse the local group of k nodes
            // Standard in-place link reversal loop
            for (int i = 0; i < k - 1; i++) {
                curr.next = nxt.next;
                nxt.next = pointer.next;
                pointer.next = nxt;
                nxt = curr.next;
            }
            
            // 4. Move pointer to the end of the reversed group
            pointer = curr;
        }
        
        return dummy.next;
    }
}
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
    public ListNode swapPairs(ListNode head) {
        // 1. Create a dummy node that acts as a stable anchor before the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 2. 'prev' track the node right before the current pair being swapped
        ListNode prev = dummy;
        
        // 3. Ensure there are at least two nodes left to swap
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // --- Adjusting the Pointers ---
            first.next = second.next;  // Step 1: Connect first node to the rest of the list
            second.next = first;       // Step 2: Point second node back to the first node
            prev.next = second;        // Step 3: Link the previous part of the list to the new pair head
            
            // 4. Move 'prev' two steps forward, placing it right before the next pair
            prev = first;
        }
        
        // The dummy's next pointer securely references the new head of the modified list
        return dummy.next;
    }
}
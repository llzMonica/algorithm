package com.yc.bean3_datatype3.tree2.AvlTree;

import com.yc.bean3_datatype3.tree1.BinarySearchTree.BinaryNode;
import com.yc.bean3_datatype3.tree1.BinarySearchTree.BinarySearchTree;

/**
 * ƽ�������: ƽ�������Ҫ�����ÿһ���ڵ���˵���������������ĸ߶�֮��ܳ���1������������ɾ��һ���ڵ�ʹ�ø߶�֮�����1����Ҫ���нڵ�֮�����ת��
 * ������������ά����һ��ƽ��״̬��<br />
 *
 * ƽ�������ʵ�ֵĴ󲿷ֹ��̺Ͷ����������һ���ģ�ѧƽ�������֮ǰһ��Ҫ����������������������ڲ����ɾ��֮��Ҫдһ����ת�㷨ȥά��ƽ�⣬
 * ά��ƽ����Ҫ����һ���ڵ�߶ȵ����ԡ�<br />
 * 
 * 
 * public int height() :
 * ��ת�㷨��Ҫ������һ�����ܵĸ����������ĸ߶ȡ�����涨��һ�ÿ����ĸ߶�Ϊ-1��ֻ��һ�����ڵ�����ĸ߶�Ϊ0���Ժ�ÿ��һ��߶ȼ�1 <br />
 * private int height(AvlNode root) �� <br />
 * 
 * private int max(int lhs, int rhs): //�������ֵ�еĴ�ֵ <br />
 * 
 * <br />
 * ***********************************ƽ�����е���ת���******************************
 * <br />
 * private AvlNode leftLeftRotation(AvlNode k2): // ����ת�� ������ת�����<br />
 * private AvlNode rightRightRotation(AvlNode k1): ����ת��������ת�����<br />
 * private AvlNode leftRightRotation(AvlNode k3) �� ���Ҷ�Ӧ�����(��˫��ת) <br />
 * private AvlNode rightLeftRotation(AvlNode k1) �����Ӧ�����(��˫��ת)<br />
 * 
 * <br />
 * *********************************��д�˸����remove��insert������ʵ��AvlTree�������ʱҪ��ת���Ĺ���*
 * **********************<br />
 * public void insert(Comparable element): ���Ԫ�ص�AvlTree�� <br />
 * private AvlNode insert(Comparable x, AvlNode t) <br />
 * public void remove(Comparable x) : ��AvlTree��ɾ��Ԫ��x<br />
 * private AvlNode remove(AvlNode root, Comparable x)
 */
public class AvlTree extends BinarySearchTree {

	

	/**
	 * ��ת�㷨��Ҫ������һ�����ܵĸ����������ĸ߶ȡ�����涨��һ�ÿ����ĸ߶�Ϊ-1��ֻ��һ�����ڵ�����ĸ߶�Ϊ0���Ժ�ÿ��һ��߶ȼ�1<br />
	 * 
	 * @return
	 */
	public int height() {
		return height((AvlNode) root);
	}

	private int height(AvlNode t) {
		return t == null ? -1 : t.height;
	}

	/**
	 * ������ֵ
	 */
	private int max(int lhs, int rhs) {
		return lhs > rhs ? lhs : rhs;
	}

	/**
	 * ����ת�� ������ת�����
	 */
	private AvlNode leftLeftRotation(AvlNode k2) {
		AvlNode k1 = (AvlNode) k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height((AvlNode) k2.left), height((AvlNode) k2.right)) + 1;
		k1.height = max(height((AvlNode) k1.left), k2.height) + 1;
		return k1;
	}

	/**
	 * ����ת��������ת�����
	 */
	private AvlNode rightRightRotation(AvlNode k1) {
		AvlNode k2 = (AvlNode) k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height((AvlNode) k1.left), height((AvlNode) k1.right)) + 1;
		k2.height = max(height((AvlNode) k2.right), k1.height) + 1;
		return k2;
	}

	/**
	 * ���Ҷ�Ӧ�����(��˫��ת)
	 */
	private AvlNode leftRightRotation(AvlNode k3) {
		k3.left = rightRightRotation((AvlNode) k3.left);
		return leftLeftRotation(k3);
	}

	/**
	 * �����Ӧ�����(��˫��ת)
	 */
	private AvlNode rightLeftRotation(AvlNode k1) {
		k1.right = leftLeftRotation((AvlNode) k1.right);
		return rightRightRotation(k1);
	}

	public void insert(Comparable element) {
		root=insert(element, (AvlNode) root);
	}

	/**
	 * @param x
	 *            Ҫ����Ľڵ�
	 * @param t
	 *            ���ĸ��ڵ�
	 * @return �µĸ��ڵ�
	 */
	private AvlNode insert(Comparable x, AvlNode t) {
		if (t == null)
			t = new AvlNode(x, null, null);
		else if (x.compareTo(t.element) < 0) { // x��ֵС�� t.element,��ŵ�������.
			t.left = insert(x, (AvlNode) t.left); // �ݹ���뵽������
			// ����֦�ڵ�߶Ȳ�Ϊ2
			if (height((AvlNode) t.left) - height((AvlNode) t.right) == 2)
				if (x.compareTo(t.left.element) < 0)
					t = leftLeftRotation(t);
				else
					t = leftRightRotation(t);
		} else if (x.compareTo(t.element) > 0) { // x��ֵ���� t.element,��ŵ�������.
			t.right = insert(x, (AvlNode) t.right); // �ݹ��������.
			// TODO:
			if (height((AvlNode) t.right) - height((AvlNode) t.left) == 2)
				if (x.compareTo(t.right.element) > 0)
					t = rightRightRotation(t);
				else
					t = rightLeftRotation(t);
		} else
			; // ����Ľڵ���t��element��ͬ�������.
		// �½ڵ�ĸ߶�Ϊ����������߸߶�+1
		t.height = max(height((AvlNode) t.left), height((AvlNode) t.right)) + 1;
		return t;
	}

	/**
	 * ɾ�� <br />
	 * 
	 * @param c:
	 *            Ҫɾ����Ԫ��
	 */
	public void remove(Comparable x) {
		AvlNode n = null;
		if ((n = (AvlNode) super.find(x,root)) != null) {
			root = remove((AvlNode) root, n);
		}
	}

	private AvlNode remove(AvlNode tree, AvlNode z) {
		// ��Ϊ�� ���� û��Ҫɾ���Ľڵ㣬ֱ�ӷ���null��
		if (tree == null || z == null)
			return null;

		int cmp = z.element.compareTo(tree.element);
		if (cmp < 0) { // ��ɾ���Ľڵ���"tree��������"��
			tree.left = remove((AvlNode) tree.left, z);
			// ɾ���ڵ����AVL��ʧȥƽ�⣬�������Ӧ�ĵ��ڡ�
			if (height((AvlNode) tree.right) - height((AvlNode) tree.left) == 2) {
				AvlNode r = (AvlNode) tree.right;
				if (height((AvlNode) r.left) > height((AvlNode) r.right))
					tree = rightLeftRotation(tree);
				else
					tree = rightRightRotation(tree);
			}
		} else if (cmp > 0) { // ��ɾ���Ľڵ���"tree��������"��
			tree.right = remove((AvlNode) tree.right, z);
			// ɾ���ڵ����AVL��ʧȥƽ�⣬�������Ӧ�ĵ��ڡ�
			if (height((AvlNode) tree.left) - height((AvlNode) tree.right) == 2) {
				AvlNode l = (AvlNode) tree.left;
				if (height((AvlNode) l.right) > height((AvlNode) l.left))
					tree = leftRightRotation(tree);
				else
					tree = leftLeftRotation(tree);
			}
		} else { // tree�Ƕ�ӦҪɾ���Ľڵ㡣
			// tree�����Һ��Ӷ��ǿ�
			if ((tree.left != null) && (tree.right != null)) {
				if (height((AvlNode) tree.left) > height((AvlNode) tree.right)) {
					// ���tree�����������������ߣ�
					// ��(01)�ҳ�tree���������е����ڵ�
					// (02)�������ڵ��ֵ��ֵ��tree��
					// (03)ɾ�������ڵ㡣
					// ����������"tree�������������ڵ�"��"tree"������
					// �������ַ�ʽ�ĺô��ǣ�ɾ��"tree�������������ڵ�"֮��AVL����Ȼ��ƽ��ġ�
					AvlNode max = (AvlNode) findMax(tree.left);
					tree.element = max.element;
					tree.left = remove((AvlNode) tree.left, max);
				} else {
					// ���tree��������������������(��������ȣ�������������������1)
					// ��(01)�ҳ�tree���������е���С�ڵ�
					// (02)������С�ڵ��ֵ��ֵ��tree��
					// (03)ɾ������С�ڵ㡣
					// ����������"tree������������С�ڵ�"��"tree"������
					// �������ַ�ʽ�ĺô��ǣ�ɾ��"tree������������С�ڵ�"֮��AVL����Ȼ��ƽ��ġ�
					AvlNode min = (AvlNode) findMax(tree.right);
					tree.element = min.element;
					tree.right = remove((AvlNode) tree.right, min);
				}
			} else {
				AvlNode tmp = tree;
				tree = (AvlNode) ((tree.left != null) ? tree.left : tree.right);
				tmp = null;
			}
		}

		return tree;
	}

	private static int arr[] = { 3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9 };

	public static void main(String[] args) {

		int i;
		AvlTree tree = new AvlTree();

		System.out.printf("== �������: ");
		for (i = 0; i < arr.length; i++) {
			System.out.printf("%d ", arr[i]);
			tree.insert(arr[i]);
		}

		System.out.printf("\n== ǰ�����: ");
		tree.printTreePreOrder();

		System.out.printf("\n== �������: ");
		tree.printTreeInOrder();

		System.out.printf("\n== �������: ");
		tree.printTreePostOrder();
		System.out.printf("\n");

		System.out.printf("== �߶�: %d\n", tree.height());
		System.out.printf("== ��Сֵ: %d\n", tree.findMin());
		System.out.printf("== ���ֵ: %d\n", tree.findMax());
		System.out.printf("== ������ϸ��Ϣ: \n");
		tree.print();
		
		System.out.println("\nɾ�����ڵ�   11:");
		tree.remove(11);
		System.out.printf("\n== ǰ�����: ");
		tree.printTreePreOrder();

		System.out.printf("\n== �������: ");
		tree.printTreeInOrder();

		System.out.printf("\n== �������: ");
		tree.printTreePostOrder();
		System.out.printf("\n");

		System.out.printf("== �߶�: %d\n", tree.height());
		System.out.printf("== ��Сֵ: %d\n", tree.findMin());
		System.out.printf("== ���ֵ: %d\n", tree.findMax());
		System.out.printf("== ������ϸ��Ϣ: \n");
		tree.print();

	}
}

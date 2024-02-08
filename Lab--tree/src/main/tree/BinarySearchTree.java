package tree;

import java.util.ArrayList;
import java.util.List;

import estrut.Tree;

public class BinarySearchTree implements Tree {

    private TreeNode root;

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRecursivo(root, valor);
    }

    private boolean buscaElementoRecursivo(TreeNode node, int valor) {
        if (node == null) {
            return false;
        }
        if (valor == node.value) {
            return true;
        } else if (valor < node.value) {
            return buscaElementoRecursivo(node.left, valor);
        } else {
            return buscaElementoRecursivo(node.right, valor);
        }
    }

    @Override
    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("Árvore vazia");
        }
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("Árvore vazia");
        }
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElementoRecursivo(root, valor);
    }

    private TreeNode insereElementoRecursivo(TreeNode node, int valor) {
        if (node == null) {
            return new TreeNode(valor);
        }
        if (valor < node.value) {
            node.left = insereElementoRecursivo(node.left, valor);
        } else if (valor > node.value) {
            node.right = insereElementoRecursivo(node.right, valor);
        }
        return node;
    }

    @Override
    public void remove(int valor) {
        root = removeRecursivo(root, valor);
    }

    private TreeNode removeRecursivo(TreeNode node, int valor) {
        if (node == null) {
            return null;
        }
        if (valor == node.value) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.value = minimo();
            node.right = removeRecursivo(node.right, node.value);
        } else if (valor < node.value) {
            node.left = removeRecursivo(node.left, valor);
        } else {
            node.right = removeRecursivo(node.right, valor);
        }
        return node;
    }

    @Override
    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdemRecursivo(root, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void preOrdemRecursivo(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrdemRecursivo(node.left, result);
            preOrdemRecursivo(node.right, result);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        emOrdemRecursivo(root, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void emOrdemRecursivo(TreeNode node, List<Integer> result) {
        if (node != null) {
            emOrdemRecursivo(node.left, result);
            result.add(node.value);
            emOrdemRecursivo(node.right, result);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        posOrdemRecursivo(root, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void posOrdemRecursivo(TreeNode node, List<Integer> result) {
        if (node != null) {
            posOrdemRecursivo(node.left, result);
            posOrdemRecursivo(node.right, result);
            result.add(node.value);
        }
    }
}

package com.bottomline.exercise.autocomplete.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Trie {
    
     class TrieNode {
        final Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
        List<String> words = new ArrayList<>();
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        String lowerWord = word.toLowerCase(); // for consistent matching
        for (char ch : lowerWord.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
            node.words.add(word); // store original case
        }
        node.isEndOfWord = true;
    }

    public List<String> autocomplete(String prefix) {
        TrieNode node = root;
        String lowerPrefix = prefix.toLowerCase(); // normalize prefix
        for (char ch : lowerPrefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return new ArrayList<>();
            }
        }
        return node.words;
    }
}

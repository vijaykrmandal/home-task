package com.bottomline.exercise.autocomplete.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.bottomline.exercise.autocomplete.entity.Name;
import com.bottomline.exercise.autocomplete.exception.InvalidPrefixException;
import com.bottomline.exercise.autocomplete.repository.NameRepository;
import com.bottomline.exercise.autocomplete.trie.Trie;

@Service
public class AutoCompleteService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NameRepository nameRepository;
    private final Trie trie;

    public AutoCompleteService(NameRepository nameRepository, Trie trie) {
        this.nameRepository = nameRepository;
        this.trie = trie;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        logger.info("Application is getting ready !!! ");

        nameRepository.findAll()
                .stream()
                .map(Name::getName)
                .filter(s -> s != null && !s.isBlank())
                .forEach(trie::insert);
    }

    public List<String> autocomplete(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            throw new InvalidPrefixException("Prefix cannot be blank");
        }
        return trie.autocomplete(prefix);
    }
}

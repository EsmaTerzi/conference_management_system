package org.cms.com.services.impl;

import org.cms.com.domain.ImportantDate;
import org.cms.com.repositories.ImportantDateRepository;
import org.cms.com.services.ImportantDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImportantDateServiceImpl implements ImportantDateService {

    private final ImportantDateRepository importantDateRepository;

    @Autowired
    public ImportantDateServiceImpl(ImportantDateRepository importantDateRepository) {
        this.importantDateRepository = importantDateRepository;
    }

    @Override
    public Page<ImportantDate> getByConferenceId(Long conferenceId, Pageable pageable) {
        return importantDateRepository.findByConferenceId(conferenceId, pageable);
    }
}


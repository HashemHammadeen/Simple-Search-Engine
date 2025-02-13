package com.zerowhisper.codesearchengine.repositories;

import com.zerowhisper.codesearchengine.models.MFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<MFile, Long> {
}

package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Marks;
import ua.kyiv.univerpulse.studentv2.mvc.dto.MarksDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.repository.MarksRepository;
import ua.kyiv.univerpulse.studentv2.mvc.service.ResultService;

import java.util.Objects;

@Service
public class ResultServiceImpl implements ResultService {

    private static final Logger logger = Logger.getLogger(ResultServiceImpl.class);

    private MarksRepository marksRepository;
    @Autowired
    public ResultServiceImpl(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    @Override
    public Integer getTotalResult(MarksDto marksDto) {
        return marksDto.getAlgebra() + marksDto.getBiology() + marksDto.getChemistry()
                + marksDto.getComputer_science() + marksDto.getDrawing() + marksDto.getEnglish()
                + marksDto.getGeography() + marksDto.getGeometry() + marksDto.getLiterature()
                + marksDto.getMusic() + marksDto.getPhysics();
    }

    @Override
    public Integer getTotalScoring(PersonDto personDto) {
        if (logger.isDebugEnabled())
            logger.debug("In ResultService person.id = " + personDto.getId());
        Marks marks = marksRepository.findMarksById(personDto.getId());
        if (Objects.nonNull(marks)) {
            MarksDto marksDto = new MarksDto.Builder().setAlgebry(marks).setBiology(marks)
                    .setChemistry(marks).setComputer_science(marks).setDrawing(marks).setEnglish(marks)
                    .setGeography(marks).setGeometry(marks).setLiterature(marks).setMusic(marks)
                    .setPhysics(marks).build();
            return getTotalResult(marksDto);
        }
        return 0;
    }

}

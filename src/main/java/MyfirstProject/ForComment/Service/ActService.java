package MyfirstProject.ForComment.Service;

import MyfirstProject.ForComment.Domain.Act;
import MyfirstProject.ForComment.Domain.Member;
import MyfirstProject.ForComment.Repository.ActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ActService {

    private final ActRepository actRepository;
@Autowired
public ActService(ActRepository actRepository) {
        this.actRepository = actRepository;
    }
    @Transactional
    public Long join(Act act){
        validateDuplicateAct(act);
        actRepository.save(act);
        return act.getId();
    }
    public void validateDuplicateAct(Act act){
        List<Act> findActs=actRepository.findByName(act.getName());
        if(!findActs.isEmpty()){
            throw new IllegalStateException("이미 존재하는 행동입니다");
        }
    }
    public List<Act> findActs(){
        return actRepository.findAll();
    }
    public Act findOne(Long actId){
        return actRepository.findOne(actId);
    }
    @Transactional
    public void updateAct(Long Id,String name,String status){
        Act act=actRepository.findOne(Id);
        act.setName(name);
        act.setStatus(status);
    }
}

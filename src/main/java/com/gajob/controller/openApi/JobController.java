package com.gajob.controller.openApi;

import com.gajob.dto.openApi.JobDto;
import com.gajob.entity.openApi.Job;
import com.gajob.service.openApi.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/issue")
public class JobController {

    private final JobService jobService;

    @PostMapping("/job-posting")
    public ResponseEntity<JobDto> saveJob() throws Exception {
        return ResponseEntity.ok(jobService.saveJob(new JobDto(Job.builder().build())));
    }

    @GetMapping("/job-posting")
    public ResponseEntity<JobDto> getJob() {
        return new ResponseEntity(jobService.getJob(), HttpStatus.OK);
    }

}

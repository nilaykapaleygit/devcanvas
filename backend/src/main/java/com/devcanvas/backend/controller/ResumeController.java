package com.devcanvas.backend.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devcanvas.backend.dto.ResumeDto;
import com.devcanvas.backend.service.ResumeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ResumeController {

	private final ResumeService resumeService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResumeDto> uploadResume(
			 @RequestParam("name")
		        String name,

		        @RequestParam("jobTitle")
		        String jobTitle,

		        @RequestParam("experience")
		        String experience,

		        @RequestParam("skills")
		        String skills,

		        @RequestParam("file")
		        MultipartFile file) {

		    return ResponseEntity.ok(
		            resumeService.uploadResume(
		                    name,
		                    jobTitle,
		                    experience,
		                    skills,
		                    file
		            )
		    );
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ResumeDto> getResume(@PathVariable Long id){
		return ResponseEntity.ok(resumeService.getResume(id));
	}
	

	@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        return ResponseEntity.ok(
                resumeService.getAllResumes()
        );
    }
    

    @GetMapping("/public")
    public ResponseEntity<ResumeDto> getPublicResume() {
        return ResponseEntity.ok(
                resumeService.getPublicResume()
        );
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/select")
    public ResponseEntity<ResumeDto> selectPublicResume(@PathVariable Long id){
    	return ResponseEntity.ok(resumeService.selectPublicResume(id));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(
            @PathVariable Long id) {

        resumeService.deleteResume(id);

        return ResponseEntity.noContent().build();
    }
    
    
    @Operation(summary = "View resume PDF")
    @ApiResponse(
        responseCode = "200",
        description = "Resume PDF",
        content = @Content(
            mediaType = MediaType.APPLICATION_PDF_VALUE,
            schema = @Schema(type = "string", format = "binary")
        )
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/view")
    public ResponseEntity<Resource> viewResume(
            @PathVariable Long id) {

        ResumeDto resume = resumeService.getResume(id);

        return viewResumeFile(resume);
    }
    

    @GetMapping("/public/view")
    public ResponseEntity<Resource> viewPublicResume() {

        ResumeDto resume = resumeService.getPublicResume();

        return viewResumeFile(resume);
    }
    
    private ResponseEntity<Resource> viewResumeFile(
            ResumeDto resume) {

        try {

            Path path = Paths.get(resume.getFilePath());

            Resource resource =
                    new UrlResource(path.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                        resume.getFileName() +
                        "\""
                    )
                    .body(resource);

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
    
   
}

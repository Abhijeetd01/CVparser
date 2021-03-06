package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;
import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantExperiences;

/**
 * Fetches data to be stored in {@link ApplicantExperiences} The result would be
 * a list of ApplicantExperiences. For instance, if you have more than one
 * applicants. You'll get:
 * 
 * ApplicantExperiencesObject = { ApplicantExperiences [id=1, experience=...],
 * ApplicantExperiences [id=2, experience=...], ApplicantExperiences [id=3,
 * experience=...] }
 * 
 * @author tramyardg
 *
 */
public class ParseApplicantExperience {
    List<ApplicantDocument> applicantDocument = new ArrayList<>();
    List<ApplicantExperiences> applicantExperienceList = new ArrayList<>();

    public ParseApplicantExperience(List<ApplicantDocument> applicantDocument) {
	this.applicantDocument = applicantDocument;
    }

    public List<ApplicantExperiences> getApplicantExperience() {
	return applicantExperienceList;
    }

    private String findWorkExperiences(int id, String line) {
	ParserHelper parser = new ParserHelper();
	/*
	 * Algorithm: copy texts starting from experience section index to the
	 * following section index experience index is LESS THAN the following
	 * section index, therefore
	 * 
	 * Example: section indexes [24, 355, 534, 669] index of experience
	 * section = 355 therefore, the following section index would be 534 we
	 * can get the texts that encompasses experience section by substring =>
	 * (indexOfExperience, beginIndexOfFollowingSection)
	 * 
	 */
	int indexOfExperience = parser.getIndexOfThisSection(RegEx.EXPERIENCE, line);
	if (indexOfExperience != -1) {
	    int nextSectionIndex = 0; // index that follows experience section
	    String experiencesText = line.replaceFirst(RegEx.EXPERIENCE.toString(), "");
	    for (int index = 0; index < parser.getAllSectionIndexes(line).size(); index++) {
		if (parser.getAllSectionIndexes(line).get(index) == indexOfExperience) {
		    // experience section is not always in the middle
		    // rarely they may appear as the last section
		    if (index == parser.getAllSectionIndexes(line).size() - 1) {
			return experiencesText.substring(indexOfExperience);
		    } else {
			nextSectionIndex = parser.getAllSectionIndexes(line).get(index + 1);
			break;
		    }
		}
	    }
	    return experiencesText.substring(indexOfExperience, nextSectionIndex);
	}
	return null;
    }

    public void setApplicantExperiences() {
	for (ApplicantDocument ad : applicantDocument) {
	    ApplicantExperiences applicantExperience = new ApplicantExperiences();
	    applicantExperience.setId(ad.getId());
	    applicantExperience.setExperience(findWorkExperiences(ad.getId(), ad.getLine()));
	    this.applicantExperienceList.add(applicantExperience);
	}
    }
}

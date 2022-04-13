package com.gajob.service.openApi;

import com.gajob.dto.openApi.JobDto;
import com.gajob.dto.openApi.JobResponseDto;
import com.gajob.entity.openApi.Job;
import com.gajob.repository.openApi.JobRepository;
import lombok.RequiredArgsConstructor;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private static String jsonPrintString;

    private final String URL = "http://openapi.work.go.kr/opi/opi/opia/wantedApi.do?" +
            "authKey=WNL1ABOSOGWABZ780W5S92VR1HJ" +
            "&callTp=L&returnType=XML&display=100";

    // 각각의 페이지를 보여주는 url 최종 완성
    public String getJobUrl(int page) {
        return URL + "&startPage=" + page;
    }

    // xml을 json으로 변환
    @Transactional
    @Override
    public String changeJson() {
        StringBuffer result = new StringBuffer();
        jsonPrintString = null;

        try {
            String apiUrl = getJobUrl(1);
            URL url = new URL(apiUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));

            String returnLine;
            while ((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine);
            }

            org.json.JSONObject jsonObject1 = XML.toJSONObject(result.toString());
            jsonPrintString = jsonObject1.toString();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return jsonPrintString;
    }

    // json으로 변환한 데이터를 db에 저장
    @Transactional
    @Override
    public JobDto saveJob(JobDto jobDto) throws Exception {
        StringBuffer result = new StringBuffer();

        try {
            changeJson();   // xml을 json으로 변환해주는 메서드 호출

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject2 = (JSONObject) jsonParser.parse(jsonPrintString);

            JSONObject parse_wantedRoot = (JSONObject) jsonObject2.get("wantedRoot");
            JSONArray parse_wanted = (JSONArray) parse_wantedRoot.get("wanted");

            // jsonParser를 이용하여 json 정보 추출
            for (int j = 0; j < parse_wanted.size(); j++) {
                JSONObject info = (JSONObject) parse_wanted.get(j);

                String career = (String) info.get("career");
                Long employTypeCode = (Long) info.get("empTpCd");
                Long lastModifyDate = (Long) info.get("smodifyDtm");
                String infoSource = (String) info.get("infoSvc");
                String title = (String) info.get("title");
                String closeDate = (String) info.get("closeDt");
                String basicAddress = (String) info.get("basicAddr");
                String prefCode = (String) info.get("prefCd");
                String registrationDate = (String) info.get("regDt");
                String wantedMobileInfoUrl = (String) info.get("wantedMobileInfoUrl");
                String workType = (String) info.get("holidayTpNm");
                Long maxSalary = (Long) info.get("maxSal");
                String wantedInfoUrl = (String) info.get("wantedInfoUrl");
                String salaryType = (String) info.get("salTpNm");
                String minEdu = (String) info.get("minEdubg");
                Long minSalary = (Long) info.get("minSal");
                String company = (String) info.get("company");
                Long streetNameAddress = (Long) info.get("strtnmCd");
                Long jobCode = (Long) info.get("jobCd");
                String authNum = (String) info.get("wantedAuthNo");
                String region = (String) info.get("region");
                String salary = (String) info.get("sal");

                Job job = new Job(career, employTypeCode, lastModifyDate, infoSource, title, closeDate,
                        basicAddress, prefCode, registrationDate, wantedMobileInfoUrl, workType, maxSalary,
                        wantedInfoUrl, salaryType, minEdu, minSalary, company, streetNameAddress, jobCode,
                        authNum, region, salary);

                jobRepository.save(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // DB에 저장한 채용 정보 읽어오기
    @Transactional
    @Override
    public List<JobResponseDto> getJob() {
        List<JobResponseDto> jobResponseDtos = new ArrayList<>();

        for (Job job : jobRepository.findAll()) {
            JobResponseDto jobResponseDto = new JobResponseDto(job);
            jobResponseDtos.add(jobResponseDto);
        }
        return jobResponseDtos;
    }
}

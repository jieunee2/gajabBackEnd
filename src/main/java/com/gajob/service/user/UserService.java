package com.gajob.service.user;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

  Long signup(@RequestBody Map<String, String> user);

  String login(@RequestBody Map<String, String> user);

}

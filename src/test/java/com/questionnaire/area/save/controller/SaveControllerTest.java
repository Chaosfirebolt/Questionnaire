package com.questionnaire.area.save.controller;

import com.questionnaire.area.save.dto.view.SaveView;
import com.questionnaire.area.save.service.interfaces.SaveLoadService;
import com.questionnaire.interceptor.game.ValidGameInterceptor;
import com.questionnaire.interceptor.question.QuestionValidationInterceptor;
import com.questionnaire.interceptor.user.DisabledFacebookUserInterceptor;
import com.questionnaire.interceptor.user.FacebookUserRegistrationInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ChaosFire on 3.5.2017 г
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = SaveController.class, secure = false)
@ActiveProfiles("test")
public class SaveControllerTest {

    private static final long SAVE_ID = 1;
    private static final long GAME_ID = 1;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SaveLoadService saveLoadService;
    @MockBean
    private FacebookUserRegistrationInterceptor facebookUserRegistrationInterceptor;
    @MockBean
    private DisabledFacebookUserInterceptor disabledFacebookUserInterceptor;
    @MockBean
    private QuestionValidationInterceptor questionValidationInterceptor;
    @MockBean
    private ValidGameInterceptor validGameInterceptor;

    @Before
    public void setUp() throws Exception {
        List<SaveView> saveViewList = new ArrayList<>();
        saveViewList.add(new SaveView(SAVE_ID, new Date()));
        when(this.saveLoadService.getPlayerSaves(1)).thenReturn(saveViewList);
        when(this.saveLoadService.loadGame(SAVE_ID)).thenReturn(GAME_ID);
    }

    @Test
    public void getAllSaves_ExpectedStatusOk() throws Exception {
        this.mvc.perform(get("/saves"))
        .andExpect(status().isOk());
    }

    @Test
    public void loadGame_RedirectionLinkCreationTest() throws Exception {
        long gameId = this.saveLoadService.loadGame(SAVE_ID);

        String expectedUrl = "/games/1/question";
        String actualUrl = "/games/" + gameId + "/question";
        assertEquals(expectedUrl, actualUrl);
    }
}
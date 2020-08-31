package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTestSuite {

    @Mock
    TrelloValidator trelloValidator;

    @Test
    public void validateCardTest(){
        //Given
        TrelloCard trelloCard = new TrelloCard ("Card", "Description", "top", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        verify(trelloValidator, times(1)).validateCard(trelloCard);
    }
    @Test
    public void validateTrelloBoardsTest(){
        //Given
        TrelloList trello = new TrelloList ("1", "list_name", true);
        List<TrelloList> trelloList = Collections.singletonList(trello);
        TrelloBoard trelloBoard = new TrelloBoard ("1", "name", trelloList);
        List<TrelloBoard> trelloBoardList = Collections.singletonList(trelloBoard);
        TrelloValidator trelloValidator = new TrelloValidator ();
        //When
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards (trelloBoardList);
        //Then
        Assert.assertNotNull(validatedList);
        Assert.assertEquals(1, validatedList.size ());
        Assert.assertEquals ("1", validatedList.get (0).getId());
        Assert.assertEquals ("name", validatedList.get (0).getName());
    }
}

package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    AdminConfig adminConfig;

    @Mock
    SimpleEmailService simpleEmailService;

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1","list_name",false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("2","board_name",trelloLists));
        when ( trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        //Then
        Assert.assertNotNull ( trelloBoardDtos );
        Assert.assertEquals ( 1, trelloBoardDtos.size () );

        trelloBoardDtos.forEach ( trelloBoardDto -> {
            Assert.assertEquals ( "2", trelloBoardDto.getId () );
            Assert.assertEquals ( "board_name", trelloBoardDto.getName () );
            trelloBoardDto.getLists ().forEach ( trelloListDto -> {
                Assert.assertEquals ( "1", trelloListDto.getId () );
                Assert.assertEquals ( "list_name", trelloListDto.getName () );
                Assert.assertFalse ( trelloListDto.isClosed () );
            } );
        } );
    }

    @Test
    public void createTrelloCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto( "name", "description", "position", "listId" );
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto ( "idDto", "nameDto", "shortUrl", new Badges(1,new AttachmentByType(new Trello(2,2))));
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto newCard = trelloService.createdTrelloCard(trelloCardDto);
        //Then
        Assert.assertEquals("idDto", newCard.getId());
        Assert.assertEquals("nameDto", newCard.getName());
        Assert.assertEquals("shortUrl", newCard.getShortUrl());
    }
}

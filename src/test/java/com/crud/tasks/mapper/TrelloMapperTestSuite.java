package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void mapToBoards(){
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","list_name_1",false);
        List<TrelloListDto> trelloListDtos = Collections.singletonList(trelloListDto);
        List<TrelloBoardDto> boardDtos = Collections.singletonList(new TrelloBoardDto("2","board_name_2",trelloListDtos));
        //When
        List<TrelloBoard> mappingResult = trelloMapper.mapToBoards(boardDtos);
        //Then
        Assert.assertEquals("2",mappingResult.get(0).getId());
        Assert.assertEquals("board_name_2",mappingResult.get(0).getName());
        Assert.assertEquals(1,mappingResult.get(0).getLists().size());
    }

    @Test
    public void mapToBoardsDto(){
        //Given
        TrelloList trelloList = new TrelloList("3","list_name_3",false);
        List<TrelloList> trelloLists = Collections.singletonList(trelloList);
        List<TrelloBoard> boards = Collections.singletonList(new TrelloBoard("4","board_name_4",trelloLists));
        //When
        List<TrelloBoardDto> mappingResult = trelloMapper.mapToBoardsDto(boards);
        //Then
        Assert.assertEquals("4",mappingResult.get(0).getId());
        Assert.assertEquals("board_name_4",mappingResult.get(0).getName());
        Assert.assertEquals(1,mappingResult.get(0).getLists().size());
    }

    @Test
    public void mapToLists(){
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("5","list_name_5",false);
        TrelloListDto trelloListDto2 = new TrelloListDto("6","list_name_6",true);
        List<TrelloListDto> trelloListDtos = Arrays.asList(trelloListDto, trelloListDto2);
        //When
        List<TrelloList> mappingResult = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(2,mappingResult.size());
        Assert.assertEquals("5",mappingResult.get(0).getId());
        Assert.assertEquals("list_name_5",mappingResult.get(0).getName());
        Assert.assertFalse(mappingResult.get(0).isClosed());
        Assert.assertEquals("6",mappingResult.get(1).getId());
        Assert.assertEquals("list_name_6",mappingResult.get(1).getName());
        Assert.assertTrue(mappingResult.get(1).isClosed());
    }

    @Test
    public void mapToListsDto(){
        //Given
        TrelloList trelloList = new TrelloList("7","list_name_7",false);
        TrelloList trelloList2 = new TrelloList("8","list_name_8",false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList, trelloList2);
        //When
        List<TrelloListDto> mappingResult = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(2,mappingResult.size());
        Assert.assertEquals("7",mappingResult.get(0).getId());
        Assert.assertEquals("list_name_7",mappingResult.get(0).getName());
        Assert.assertFalse(mappingResult.get(0).isClosed());
        Assert.assertEquals("8",mappingResult.get(1).getId());
        Assert.assertEquals("list_name_8",mappingResult.get(1).getName());
        Assert.assertFalse(mappingResult.get(1).isClosed());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("name_9","description_9","top","listId_9");
        //When
        TrelloCard mappingResult = trelloMapper.mapToCard(cardDto);
        //Then
        Assert.assertEquals("name_9",mappingResult.getName());
        Assert.assertEquals("description_9",mappingResult.getDescription());
        Assert.assertEquals("top",mappingResult.getPos());
        Assert.assertEquals("listId_9",mappingResult.getListId());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("name_10","description_10","bottom","listId_10");
        //When
        TrelloCardDto mappingResult = trelloMapper.mapToCardDto(card);
        //Then
        Assert.assertEquals("name_10",mappingResult.getName());
        Assert.assertEquals("description_10",mappingResult.getDescription());
        Assert.assertEquals("bottom",mappingResult.getPos());
        Assert.assertEquals("listId_10",mappingResult.getListId());
    }
}
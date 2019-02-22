package org.example.palabres.business.impl.manager;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.example.palabres.business.SpringTestConfig;
import org.example.palabres.model.exception.NotFoundException;
import org.example.palabres.model.exception.TechnicalException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
public class ChatManagerImplTest {

    @Inject
    private ChatManagerImpl chatManager;


    @Test
    public void getListChannel() {
        Assert.assertNotEquals(0, chatManager.getListChannel().size());
    }


    @Test
    public void getChannel() throws NotFoundException {
        Assert.assertNotNull(chatManager.getChannel("Kaamelott"));
    }

    @Test(expected = NotFoundException.class)
    public void testgetUtilisateurNFE() throws NotFoundException {
        Assert.assertNotNull(chatManager.getChannel("UNKNOWN_CHANNEL"));
    }


    @Test
    @DirtiesContext
    public void getListNewMessage() throws TechnicalException, NotFoundException {
        chatManager.setFakeAddMessageMin(2);
        chatManager.setFakeAddMessageMax(2);
        Assert.assertEquals(0, chatManager.getListNewMessage(chatManager.getChannel("Kaamelott"), null).size());
        Assert.assertEquals(2, chatManager.getListNewMessage(chatManager.getChannel("Kaamelott"), null).size());
        Assert.assertEquals(2, chatManager.getListNewMessage(chatManager.getChannel("Kaamelott"), 1).size());
        Assert.assertEquals(6, chatManager.getListNewMessage(chatManager.getChannel("Kaamelott"), null).size());
    }

    @Test( expected = TechnicalException.class)
    @DirtiesContext
    public void getListNewMessageTE() throws TechnicalException, NotFoundException {
        Assert.assertEquals(0, chatManager.getListNewMessage(chatManager.getChannel("Kaamelott"), 0).size());
    }

}

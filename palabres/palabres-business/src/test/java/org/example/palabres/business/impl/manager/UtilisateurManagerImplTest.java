package org.example.palabres.business.impl.manager;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.example.palabres.business.SpringTestConfig;
import org.example.palabres.model.exception.NotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
public class UtilisateurManagerImplTest {

    @Inject
    private UtilisateurManagerImpl utilisateurManager;

    @Test
    public void getListUtilisateur() {
        Assert.assertNotEquals(0, utilisateurManager.getListUtilisateur().size());
    }

    @Test
    public void getUtilisateur() throws NotFoundException {
        Assert.assertNotNull(utilisateurManager.getUtilisateur("Karadoc"));
    }

    @Test(expected = NotFoundException.class)
    public void getUtilisateurNFE() throws NotFoundException {
        Assert.assertNotNull(utilisateurManager.getUtilisateur("UNKNOWN_USER"));
    }


}

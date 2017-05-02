/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Remote;

/**
 *
 * @author Amaury
 */
@Remote
public interface ServiceBanqueRemote {
    
    long chercherClient(String nom, String prenom) throws exceptions.ExceptionClient;
    
}

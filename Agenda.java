package com.emilly.tema6;

import com.emilly.tema6.Contato;

import java.util.ArrayList;
import java.util.Arrays;

public class Agenda {
	
    private List<Contato> agenda = new ArrayList<>();

    public void adicionarContato(Contato contato) {
      	if (contato.getNomeContato() != null && contato.getNomeContato() != "" && contato.getNumeroContato() != null && contato.getNumeroContato() != "" && contato.getIdContato() != null) {
            if (contato.getNumeroContato().matches("^[0-9]+$")) {
		agenda.add(contato);
		return true;
	    } else {
		return false;
	    }
	} else {
	  return false;
	}
    }

    public boolean removerContato(String nomeId) {

        Optional<Integer> numero = verificaNumero(nomeId);

        numero.ifPresentOrElse(
                (value)
                        ->
                {
                    Optional<Contato> contato = buscarContatoPorId(value);
                    if (contato.isPresent()) {
                        agenda.remove(contato.get());
                        return true;

                    } else {
                        return false;

                    }
                },
                ()
                        ->
                {
                    Optional<Contato> contato = buscarContatoPorNome(nomeId);
                    if (contato.isPresent()) {
                        agenda.remove(contato.get());
                        return true;

                    } else {
                        return false;

                    }
                }
        );

    }

    public void listarAtributos(Optional<Contato> contato) {

        contato.ifPresentOrElse(
                (value)
                        ->
                {
                    System.out.println("Contato: " + value.getNomeContato());
                    System.out.println("Id " + value.getIdContato());
                    System.out.println("Numero: " + value.getNumeroContato());
                },
                ()
                        ->
                {
                    System.out.println("Este contato não existe");
                }
        );

    }
    public ArrayList<Contato> getAgenda() {
            return agenda;

    }

   public Optional<Contato> buscarContatoPorId(Integer id) {

        for (Contato c : agenda)
            if (c.getIdContato().equals(id)) {
                return Optional.of(c);
            }

        return Optional.empty();
    }
	  

    private Optional<Integer> verificaNumero(String texto) {

        if ((texto.matches("^[0-9]+$"))) {
            Integer inteiro = Integer.parseInt(texto);
	    return Optional.of(inteiro);
        } 
            return Optional.empty();
    }

    public Optional<Contato> buscarContatoPorNome(String nome) {

        for (Contato c : agenda)
            if (c.getNomeContato().equalsIgnoreCase(nome)) {
                return Optional.of(c);
            }
        return Optional.empty();
    }
}

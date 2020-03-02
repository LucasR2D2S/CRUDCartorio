package com.docket.cartorio.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.docket.cartorio.Model.Cartorio;
import com.docket.cartorio.Repository.CartorioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartorioController {
    
    @Autowired
    private CartorioRepository cartorioRepository;
    
    @RequestMapping("/")
	public String index(){
		return "index";
    }

    @RequestMapping("post")
	public String indexpost(){
		return "indexCartorio";
    }

    @RequestMapping("indexlista")
	public String indexLista(){
		return "indexLista";
    }

    @PostMapping("cartorio")
    public String criarCartorio(@RequestParam("nome") String nome, Model model) {
        Cartorio novoCartorio = new Cartorio(nome);
        cartorioRepository.save(novoCartorio);
        model.addAttribute("cartorio", novoCartorio);
        return "index";
    }

    @PutMapping("edit")
    public String atualizarCartorio(@RequestParam("idEdit") Long cartorioId, @Valid Cartorio cartorioCorpo, Model model) {
        final Cartorio cartorio = cartorioRepository.findById(cartorioId)
                .orElseThrow(() -> new IllegalArgumentException("Id de cartorio inválido: " + cartorioId));

        cartorio.setNome(cartorioCorpo.getNome());
        final Cartorio cartorioNovo = cartorioRepository.save(cartorio);
        model.addAttribute("cartorioEditado", cartorioRepository.findById(cartorioNovo.getId()));
        return "index";
    }

    @GetMapping("get")
    public String getCartorioById(@PathVariable("idGet") Long cartorioId, Model model) {
        Cartorio cartorio = cartorioRepository.findById(cartorioId)
            .orElseThrow(() -> new IllegalArgumentException("Id de cartorio inválido: " + cartorioId));
        model.addAttribute("cartorio", cartorio);
        return "indexCartorio";
    }

    @DeleteMapping("del")
    public String deletarCartorio(@PathVariable("idDel") final Long cartorioId, Model model) {
        Cartorio cartorio = cartorioRepository.findById(cartorioId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + cartorioId));
        cartorioRepository.delete(cartorio);
        final Map<String, Boolean> response = new HashMap<>();
        model.addAttribute("cartorioExcluido", response.put("deleted", Boolean.TRUE));
        return "index";
    }

    @RequestMapping("listaCartorios")
    public String listaCartorios(final Model model) {

        final Iterable<Cartorio> cartorios = cartorioRepository.findAll();
		
		model.addAttribute("cartorios", cartorios);
		
		return "indexLista";
	}

    @GetMapping("/cartorios")
    public Iterable<Cartorio> getTodosCartorios() {
        return cartorioRepository.findAll();
    }

}
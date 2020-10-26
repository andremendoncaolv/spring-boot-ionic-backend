package com.minhacor.appdemo;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.minhacor.appdemo.domain.Categoria;
import com.minhacor.appdemo.domain.Cidade;
import com.minhacor.appdemo.domain.Cliente;
import com.minhacor.appdemo.domain.Endereco;
import com.minhacor.appdemo.domain.Estado;
import com.minhacor.appdemo.domain.Produto;
import com.minhacor.appdemo.domain.enums.TipoCliente;
import com.minhacor.appdemo.repositories.CategoriaRepository;
import com.minhacor.appdemo.repositories.CidadeRepository;
import com.minhacor.appdemo.repositories.ClienteRepository;
import com.minhacor.appdemo.repositories.EnderecoRepository;
import com.minhacor.appdemo.repositories.EstadoRepository;
import com.minhacor.appdemo.repositories.ProdutoRepository;

@SpringBootApplication
public class AppdemoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppdemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00); 
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3 ));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia", est1);
		Cidade c2 = new Cidade(null,"Sao Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@maria.com", "33333335333", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("99999999999", "65599998989"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "jardim", "3656556", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua a", "230", "casa 03", "castelo", "111116", cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,  e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}

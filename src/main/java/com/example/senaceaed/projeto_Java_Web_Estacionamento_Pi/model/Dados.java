package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model;

import java.util.ArrayList;
import java.util.List;

public class Dados {

    private static List<Funcionario> listaFuncionarios = new ArrayList<>();
    private static List<Veiculo> listaVeiculos = new ArrayList<>();

    public static void adicionarFuncionario(Funcionario funcionario) {
        listaFuncionarios.add(funcionario);
    }

    public static List<Funcionario> listarFuncionarios() {
        return listaFuncionarios;
    }

    public static void excluirFuncionario(Long id) {
        for (Funcionario f : listaFuncionarios) {
            if (f.getId() == id) {
                listaFuncionarios.remove(f);
                break;
            }
        }
    }

    public static Funcionario obterFuncionario(Long id) {
        Funcionario funcionarioEncontrado = new Funcionario();
        for (Funcionario f : listaFuncionarios) {
            if (f.getId() == id) {
                funcionarioEncontrado = f;
                break;
            }
        }
        return funcionarioEncontrado;
    }

    public static void atualizarFuncionario(Funcionario funcionario) {
        for (Funcionario f : listaFuncionarios) {
            if (f.getId().equals(funcionario.getId())) {
                f.setNome(funcionario.getNome());
                f.setCargo(funcionario.getCargo());
                f.setCodigoFuncionario(funcionario.getCodigoFuncionario());
                break;
            }
        }
    }

    public static void adicionarVeiculo(Veiculo veiculo, Funcionario funcionario) {
        veiculo.setId((long) (listaVeiculos.size() + 1));
        veiculo.setFuncionario(funcionario);
        listaVeiculos.add(veiculo);
    }
    
    public static List<Veiculo> listaVeiculo(Long idFuncionario) {
        List<Veiculo> veiculoEncontrado = new ArrayList<>();
        for (Veiculo v : listaVeiculos) {
            if (v.getFuncionario().getId().equals(idFuncionario)) {
                veiculoEncontrado.add(v);
            }
        }
        return veiculoEncontrado;
    }

    public static List<Veiculo> listarVeiculos() {
        return listaVeiculos;
    }

    public static void excluirVeiculo(Long id) {
        for (Veiculo v : listaVeiculos) {
            if (v.getId() == id) {
                listaVeiculos.remove(v);
                break;
            }
        }
    }

    public static Veiculo obterVeiculo(Long id) {
        Veiculo veiculoEncontrado = new Veiculo();
        for (Veiculo v : listaVeiculos) {
            if (v.getId() == id) {
                veiculoEncontrado = v;
                break;
            }
        }
        return veiculoEncontrado;
    }

    public static void atualizarVeiculo(Veiculo veiculo) {
        for (Veiculo v : listaVeiculos) {
            if (v.getId().equals(veiculo.getId())) {

                v.setPlaca(veiculo.getPlaca());
                v.setModelo(veiculo.getModelo());
                if (veiculo.getValor() != null) {
                    v.setValor(veiculo.getValor());
                }
                v.setTipoPagamento(veiculo.getTipoPagamento());

                if (veiculo.getDataRegistro() != null) {
                    v.setDataRegistro(veiculo.getDataRegistro());
                }

                if (veiculo.getDataSaida() != null) {
                    v.setDataSaida(veiculo.getDataSaida());
                }

                break;
            }
        }
    }
}

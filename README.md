# Escalonador

Projeto para a disciplina de Análise e Projetos de Sistemas do curso de Licenciatura em Ciência da Computação da UFPB - Campus IV


O projeto consiste de fazer os testes passarem e escrever um bom código OO, com baixo acoplamento, alta coesão e padrões de projeto.

Os testes estão localizados na pasta src/test/java e o código da solução deve ser escrito na pasta src/main/java.

Já é provida uma Fachada para interfacear os testes e a solução. A Fachada não deve alterada, nem os testes.
 
 
Descrição dos testes:
 
- 01: Um escalonador do tipo round robin é criado e, por padrão, inicia no instante (tick) 0 (zero) e com quantum igual a 3 (três).

O Quantum representa o tempo máximo que um processo pode ocupar a CPU, se houver mais de um processo concorrento por ela.

- 02: Ao avançar o tempo, o instante atual do escalonador é incrementado.  

- 03: Adicionar um Processo no Escalonador

Como não há concorrência na CPU, o quantum é ultrapassado mas o processo não perde a CPU.

- 04: Finalizar um processo que está executando

No próximo tick, ele perderá a CPU e não voltará para a fila de processos, portanto o escalonador ficará vazio.

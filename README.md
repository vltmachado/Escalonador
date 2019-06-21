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

- 05: Dois processos concorrem pela CPU, estourar o quantum para forçar a troca de processos

Os processos se alternam na CPU a cada estouro do quantum.

- 06: Três processos concorrem pela CPU, estourar o quantum para forçar a troca de processos

Os processos se alternam na CPU a cada estouro do quantum.

- 07: Dois processos concorrem pela CPU, a partir do tick 2

Só considera o quantum a partir do próximo tick (3), quando há realmente concorrência.

- 08: Processo executando finaliza antes de estourar o quantum

O primeiro processo da fila ganha a CPU no próximo tick
 
- 09a: Finalizar processo esperando - cenário com dois processos

O processo executando não perde a CPU quando estourar o quantum

 - 09b: Finalizar processo esperando - cenário com três processos

O processo que permanece na fila recebe a CPU quando estourar o quantum

- 10: Modificar o quantum para 5

Serão precisos mais ticks para alternar os processos.

 - 11: Dois processos passam pela CPU em momentos distintos
 
 Há um intervalo sem processos na CPU.
 
 

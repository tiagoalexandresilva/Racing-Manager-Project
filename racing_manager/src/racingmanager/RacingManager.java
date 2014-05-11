package racingmanager;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RacingManager {
    private static ListaUtilizadores lista= new ListaUtilizadores();
    private static Menu menuLogin, menuCampA, menuAdmin, menuConfigUt, menuApostas, menuCampU, menuCampClass,menuApAd,menuApUt;
    private static Scanner ler= new Scanner(System.in);

    public static void main(String[] args) throws SaldoInsuficienteException, ClassificacaoErradaException, NaoExisteCircuitoException, NaoExisteNomeEquipaException {
       Piloto p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17,
                p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32,
                p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
                p48, p49, p50, p51, p52, p53, p54, p55, p56, p57, p58;
        Carro car1, car2, car3, car4, car5, car6, car7, car8, car9, car10, car11, car12,
                car13, car14, car15, car16, car17, car18, car19, car20, car21, car22,
                car23, car24, car25, car26, car27, car28;
        Circuito c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12;
        Campeonato camp1 = new Campeonato();
        Equipa e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, 
                e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32;
        Utilizador u1,u2,u3,u4;
        u1 = new Utilizador("admin","admin","admin","admin");
        u2 = new Utilizador("ts","tiago","Rua do Passal","123");
        u3 = new Utilizador("sm","samuel","Rua do Zé do Povinho","123");
        u4 = new Utilizador("lo","lucas","Rua do Gelado Fresquinho","123");
        
        try {
            lista.novoRegisto(u1);
        } catch (NickJaExisteException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            lista.novoRegisto(u2);
        } catch (NickJaExisteException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            lista.novoRegisto(u3);
        } catch (NickJaExisteException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            lista.novoRegisto(u4);
        } catch (NickJaExisteException ex) {
            System.out.println(ex.getMessage());
        }
        
        u2.setSaldo(1050);
        u3.setSaldo(1001);
        u4.setSaldo(1010);
        
        double[] ar1 = {119, 130.2, 150.7, 165.3};
        double[] ar2 = {104.617, 120.356, 138.45, 150.698};
        double[] ar3 = {134.508, 145.421, 159.452, 170.194};
        double[] ar4 = {115.937, 129.654, 141.512, 156.465};
        double[] ar5 = {87.820, 99.785, 115.684, 128.319};
        double[] ar6 = {125.846, 130.269, 145.812, 158.743};
        double[] ar7 = {82.890, 95.451, 108.672, 122.620};
        double[] ar8 = {107.732, 123.493, 140.952, 154.362};
        double[] ar9 = {53.885, 66.851, 79.395, 96.542};
        double[] ar10 = {115.233, 131.009, 144.765, 160.354};
        double[] ar11 = {152.063, 166.259, 181.265, 194.365};
        double[] ard1 = {5.3, 7.5, 8.2, 10.4};
        p1 = new Piloto("Allan McNish", "Reino Unido", 0, 6, 6);
        p2 = new Piloto("Tom Kristensen", "Dinamarca", 2, 8, 7.5);
        p3 = new Piloto("Nicolas LaPierre", "França", 0, 5, 7);
        p4 = new Piloto("Kazuki Nakajima", "Japão", 0, 7, 8);
        p5 = new Piloto("Andrea Belicchi", "Itália", 0, 4, 5);
        p6 = new Piloto("Jeroen Bleekemolen", "Holanda", 1, 8, 8);
        p7 = new Piloto("Franck Montagny", "França", 0, 4, 5);
        p8 = new Piloto("Takuma Sato", "Japão", 1, 7, 9);
        p9 = new Piloto("Emmanuel Collard", "França", 2, 7, 6);
        p10 = new Piloto("Stuart Hall", "Reino Unido", 0, 7, 5);
        p11 = new Piloto("Jonny Kane", "Reino Unido", 0, 9, 4.5);
        p12 = new Piloto("Danny Watts", "Reino Unido", 1, 8, 4);
        p13 = new Piloto("Karun Chandhok", "India", 0, 9, 6);
        p14 = new Piloto("Peter Dumbreck", "Reino Unido", 0, 6, 6);
        p15 = new Piloto("Franck Mailleux", "França", 0, 4, 5);
        p16 = new Piloto("Pierre Ragues", "França", 0, 6, 8);
        p17 = new Piloto("Carlos Vieira", "Portugal", 0, 7, 5);
        p18 = new Piloto("César Campanico", "Portugal", 2, 7, 6);
        p19 = new Piloto("Frank Kechele", "Alemanha", 5, 9, 4);
        p20 = new Piloto("Michael Bartels", "Alemanha", 3, 6, 7);
        p21 = new Piloto("Fabio Onidi", "Itália", 4, 6, 5);
        p22 = new Piloto("Filip Salaquarda", "Réplublica Checa", 0, 6, 8);
        p23 = new Piloto("Raijan Mascarello", "Brasil", 1, 7, 3);
        p24 = new Piloto("Claudio Ricci", "Brasil", 0, 5, 8);
        p25 = new Piloto("Dominic Baumann", "Austria", 2, 9, 8);
        p26 = new Piloto("Hari Proczyk", "Austria", 0, 4, 7);
        p27 = new Piloto("Niclas Kentenich", "Alemanha", 0, 5, 7);
        p28 = new Piloto("Daniel Keilwitz", "Alemanha", 5, 8, 4);
        p29 = new Piloto("Jan Seyffarth", "Alemanha", 0, 7, 4.5);
        p30 = new Piloto("Duda Rosa", "Brasil", 1, 7, 3);
        p31 = new Piloto("Lucas Ordonez", "Espanha", 0, 6, 8);
        p32 = new Piloto("Wolfgang Reip", "Bélgica", 0, 5, 6);
        p33 = new Piloto("Nicolas Minassian", "França", 0, 8, 7);
        p34 = new Piloto("David Reutimann", "EUA", 3, 7, 8);
        p35 = new Piloto("Travis Kvapil", "EUA", 2, 5, 3);
        p36 = new Piloto("Denny Hamlin", "EUA", 4, 9, 3);
        p37 = new Piloto("Kyle Busch", "EUA", 3, 6, 6);
        p38 = new Piloto("Jamie McMurray", "EUA", 6, 8, 6);
        p39 = new Piloto("Juan Pablo Montoya", "Colombia", 2, 10, 3);
        p40 = new Piloto("Terry Labonte", "EUA", 2, 4, 7);
        p41 = new Piloto("Ken Schrader", "EUA", 2, 6, 5);
        p42 = new Piloto("David Ragan", "EUA", 2, 5, 2);
        p43 = new Piloto("David Gilliland", "EUA", 2, 7, 4);
        p44 = new Piloto("Kasey Kahne", "EUA", 2, 6, 6);
        p45 = new Piloto("Jimmie Johnson", "EUA", 2, 7, 2);
        p46 = new Piloto("Bas Leinders", "Bélgica", 3, 5, 9);
        p47 = new Piloto("Dominik Kraihamer", "Austria", 0, 6, 4);
        p48 = new Piloto("Mathias Beche", "Suiça", 4, 9, 6);
        p49 = new Piloto("John Martin", "Australia", 1, 6, 2);
        p50 = new Piloto("Frédéric Fatien", "Costa do Marfim", 0, 4, 2);
        p51 = new Piloto("Keiko Ihara", "Japão", 2, 7, 3);
        p52 = new Piloto("Luca Moro", "Itália", 3, 6, 7);
        p53 = new Piloto("Jan Charouz", "Républica Checa", 0, 3, 8);
        p54 = new Piloto("Elton Julian", "Colombia", 0, 5, 5);
        p55 = new Piloto("Roberto González", "Mexico", 0, 4, 7);
        p56 = new Piloto("Enzo Potolicchio", "Venezuela", 0, 4, 6);
        p57 = new Piloto("Stéphane Sarrazin", "França", 0, 6, 7);
        p58 = new Piloto("Luís Pérez Companc", "Argentina", 0, 5, 4);
        
        
        c2 = new Circuito(5793, ar1, ard1, 20, 119, 5.4, "Desconhecido", "Autodromo di Monza", false, 1);
        c3 = new Circuito(4545, ar2, ard1, 22, 104.617, 5.4, "Desconhecido", "Circuit Moulay El Hassan", false, 2);
        c4 = new Circuito(5922, ar3, ard1, 20, 134.508, 5.4, "Desconhecido", "Slovakia Ring", false, 3);
        c5 = new Circuito(4381, ar4, ard1, 24, 115.937, 5.4, "Desconhecido", "Hungaroring", false, 4);
        c6 = new Circuito(4241, ar5, ard1, 24, 87.820, 5.4, "Desconhecido", "Salzburgring", false, 5);
        c7 = new Circuito(4800, ar6, ard1, 22, 125.846, 5.4, "Desconhecido", "Circuita da Boavista", false, 6);
        c8 = new Circuito(3695, ar7, ard1, 28, 82.890, 5.4, "Desconhecido", "Autódromo Internacional de Curitiba", false, 7);
        c9 = new Circuito(4032, ar8, ard1, 26, 107.732, 5.4, "Desconhecido", "Sonoma", false, 8);
        c10 = new Circuito(2243, ar9, ard1, 52, 53.885, 5.4, "Desconhecido", "Suzuka", false, 9);
        c11 = new Circuito(4603, ar10, ard1, 26, 115.233, 5.4, "Desconhecido", "Shanghai International Circuit", false, 10);
        c12 = new Circuito(6120, ar11, ard1, 18, 152.063, 5.4, "Desconhecido", "Circuito da Guia", false, 11);
        
        car1 = new PC1("Audi", "R18 ultra", 6000, 550);
        car2 = new PC1("Toyota", "TS030", 6000, 550);
        car3 = new PC1("Lola", "B12", 6000, 550);
        car4 = new PC1("Pescarolo", "01", 6000, 550);
        car5 = new PC1("HPD", "ARX-03a", 6000, 550);
        car6 = new PC2("Oreca", "03", 4500, 450);
        car7 = new PC2("Morgan", "LMP2", 4500, 450);
        car8 = new PC2("Lola", "B12", 4500, 450);
        car9 = new PC2("Zytek", "Z11SN", 4500, 450);
        car10 = new PC2("HPD", "ARX-03b", 4500, 450);
        car11 = new GT("Audi", "R8 LMS ULTRA", 4200, 500);
        car12 = new GT("BMW", "E89 Z4", 4375, 515);
        car13 = new GT("Ferrari", "458 Italia GT3", 4390, 550);
        car14 = new GT("Ford", "GT", 4490, 550);
        car15 = new GT("Lamborghini", "Gallardo LP600", 4300, 535);
        car16 = new GT("MacLaren", "GT MP4 12C GT3", 3799, 495);
        car17 = new GT("Mercedes-benz", "SLS AMG GT3", 4400, 500);
        car18 = new GT("Nissan", "GT-R Nismo GT3", 4400, 500);
        car19 = new SC("Toyota", "Camry", 2500, 300);
        car20 = new SC("Chevrolet", "SS", 2500, 300);
        car21 = new SC("Ford", "Fusion", 2500, 300);
        car22 = new PC1_Hibrido("HPD", "ARX-03a", 6000, 500, 60);
        car23 = new PC1_Hibrido("Pescarolo", "01", 6000, 500, 65);
        car24 = new PC2_Hibrido("Oreca", "03", 4500, 400, 45);
        car25 = new PC2_Hibrido("Lola", "B12", 4500, 400, 45);
        car26 = new GT_Hibrido("Audi", "R8 LMS ULTRA", 4200, 450, 30);
        car27 = new GT_Hibrido("BMW", "E89 Z4", 4375, 460, 30);
        car28 = new GT_Hibrido("Mercedes-benz", "SLS AMG GT3", 4400, 450, 30);
        
        e1= new Equipa("Audi Sport Team Joest", p1, p2, car1);
        e2= new Equipa("Toyota Racing", p3, p4, car2);
        e3= new Equipa("Rebellion Racing", p5, p6, car3);
        e4= new Equipa("OAK Racing", p7, p8, car23);
        e5= new Equipa("Pescarolo Team", p9, p10, car4);
        e6= new Equipa("Strakka Racing", p11, p12, car22);
        e7= new Equipa("JRM", p13, p14, car5);
        
        e8= new Equipa("Signatech Nissan", p15, p16, car6);
        e9= new Equipa("Oak Racing", p46, p47, car7);
        e10= new Equipa("ADR-Delta", p48, p49, car6);
        e11= new Equipa("Gulf Racing MiddleEast", p50, p51, car8);
        e12= new Equipa("Greaves Motosport", p52, p53, car9);
        e13= new Equipa("Lotus", p54, p55, car25);
        e14= new Equipa("Starworks Motorsport", p56, p57, car10);
        e15= new Equipa("PeCom Racing", p58, p33, car24);
        
        e16= new Equipa("Nova Driver", p17, p18, car11);
        e17= new Equipa("Vita4One Racing", p19, p20, car12);
        e18= new Equipa("AF Corse", p21, p22, car13);
        e19= new Equipa("Rodrive Competicoes", p23, p24, car14);
        e20= new Equipa("Grasser Racing", p25, p26, car15);
        e21= new Equipa("Dorr Motorsport", p27, p28, car16);
        e22= new Equipa("Seyffarth Motorsport", p29, p30, car17);
        e23= new Equipa("Nissan GT Academy Team RJN", p31, p32, car18);
        e30= new Equipa("Belgian Audi Club Team WRT", p31, p32, car26);
        e31= new Equipa("BMW Sports Trophy Team India", p31, p32, car27);
        e32= new Equipa("HTP Gravitz Charouz", p31, p32, car28);
        
        e24= new Equipa("BK Racing", p34, p35, car19);
        e25= new Equipa("Joe Gibbs Racing", p36, p37, car19);
        e26= new Equipa("Earnhardt Ganassi Racing", p38, p39, car20);
        e27= new Equipa("FAS Lane Racing", p40, p41, car21);
        e28= new Equipa("Front Row Motorsports", p42, p43, car21);
        e29= new Equipa("Hendrick Motorsports", p44, p45, car21);
        camp1= new Campeonato();
        camp1.addEquipa(e1);
        camp1.addEquipa(e2);
        camp1.addEquipa(e3);
        camp1.addEquipa(e4);
        camp1.addEquipa(e5);
        camp1.addEquipa(e6);
        camp1.addEquipa(e7);
        camp1.addEquipa(e8); 
        camp1.addEquipa(e9); 
        camp1.addEquipa(e10); 
        camp1.addEquipa(e11);    
        camp1.addEquipa(e12); 
        camp1.addEquipa(e13); 
        camp1.addEquipa(e14); 
        camp1.addEquipa(e15); 
        camp1.addEquipa(e16); 
        camp1.addEquipa(e17); 
        camp1.addEquipa(e18); 
        camp1.addEquipa(e19); 
        camp1.addEquipa(e20); 
        camp1.addEquipa(e21); 
        camp1.addEquipa(e22); 
        camp1.addEquipa(e23); 
        camp1.addEquipa(e24); 
        camp1.addEquipa(e25); 
        camp1.addEquipa(e26); 
        camp1.addEquipa(e27); 
        camp1.addEquipa(e28); 
        camp1.addEquipa(e29); 
        camp1.addEquipa(e30); 
        camp1.addEquipa(e31); 
        camp1.addEquipa(e32);   
        camp1.inicializaClass();
        camp1.addCircuito(c2);
        camp1.addCircuito(c3);
        camp1.addCircuito(c4);
        camp1.addCircuito(c5);
        camp1.addCircuito(c6);
        camp1.addCircuito(c7);
        camp1.addCircuito(c8);
        camp1.addCircuito(c9);
        camp1.addCircuito(c10);
        camp1.addCircuito(c11);
        camp1.addCircuito(c12);
         
        
        carregaMenu();
        mmenuLogin(camp1,lista);
        System.out.println("\n<<<<<<<<RACING MANAGER TERMINADO>>>>>>>>");

        
    } 
    
    
    
    
    
   private static void carregaMenu(){
            String[] smenuLogin={"Login","Novo Registo"};
            
            String[] smenuCampA={"Próxima Corrida a ser simulada", "Simular Próxima Corrida", "Classificação Geral", 
                "Outras Classificações", "Equipas Participantes", "Lista dos Circuitos"};
            
            String[] smenuCampU={"Próxima Corrida", "Classificação Geral", 
                "Outras Classificações", "Equipas Participantes", "Lista dos Circuitos"};
            
            String[] smenuAdmin={"Gestão de Campeonato","Gestão de Apostas", "Guardar Dados"};
            
            String[] smenuCampClass={"Classificação Geral", "Classificação PC1", "Classificação PC2",
                "Classificação GT", "Classificação SC", "Classificação Troféu Híbrido", "Consultar Classificação de um Circuito"};
            
            String[] smenuConfigUt={"Consultar Dados do Registo","Alterar nome próprio", "Alterar password", "Alterar morada",
                "Transferir dinheiro"};
            
            String[] smenuApUt={"Consultar Movimentos de Conta","Consultar Histórico de Apostas","Consultar Apostas em Vigor","Consultar o Scoreboard","Lista de Odd's"};
            
            String[] smenuApostas={"Fazer Aposta","Configurações de Utilizador","Gestão de Apostas do Utilizador","Gestão de Campeonato"};
            
            String[] smenuApAd={"Total Histórico de Apostas", "Total Apostas em Vigor","Consultar Scoreboard","Lista de Odd's"};
            
            
            menuLogin= new Menu(smenuLogin);
            menuCampA=new Menu(smenuCampA);
            menuCampU=new Menu(smenuCampU);
            menuAdmin=new Menu(smenuAdmin);
            menuCampClass=new Menu(smenuCampClass);
            menuConfigUt=new Menu(smenuConfigUt);
            menuApostas=new Menu(smenuApostas);
            menuApAd=new Menu(smenuApAd);
            menuApUt=new Menu(smenuApUt);
        }
        
    
         public static void mmenuLogin(Campeonato camp, ListaUtilizadores lutilizadores)
        {  
        Utilizador ut;
        String nick, pass, nome, morada;
        Scanner scan = new Scanner(System.in);
        lutilizadores.inicializaListaOdds(camp.getEquipas());
        lutilizadores.inicializaScoreboard();
        do{
            System.out.println("\n<<<<<Bem-Vindo ao Racing Manager!>>>>");
            System.out.println("-------------------------------------");
            menuLogin.executa();
            switch(menuLogin.getOpcao()){
                case 1:
                    System.out.print("\nNickname: ");
                    nick = scan.nextLine();
                    System.out.print("Password: ");
                    pass = scan.nextLine();
                    try {
                        ut = lutilizadores.login(nick,pass);
                        System.out.println("\n\nLogin efectuado com sucesso!");
                        if(nick.equals("admin"))
                            mmenuAdmin(camp,lutilizadores);
                        else
                            mmenuApostas(camp,lutilizadores,ut);
                    }catch (NaoExisteUtException | PassErradaException ex) {
                        System.out.println(ex.getMessage());
                    }   

                    break;
                case 2:
                    System.out.print("\nNickname: ");
                    nick = scan.nextLine();
                    System.out.print("Password: ");
                    pass = scan.nextLine();
                    System.out.print("Nome Próprio: ");
                    nome = scan.nextLine();
                    System.out.print("Morada: ");
                    morada = scan.nextLine();
                    ut = new Utilizador(nick,nome, morada, pass);
                    try {
                        lutilizadores.novoRegisto(ut);
                        System.out.println("\nRegisto efectuado com sucesso!");
                    } catch (NickJaExisteException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }while(menuLogin.getOpcao()!=0);
    }    
    
    
         private static void mmenuApostas(Campeonato camp, ListaUtilizadores lutilizadores, Utilizador ut){
        Scanner scan = new Scanner(System.in);
        Aposta aposta;
        int circuito, classificacao;
        String nomeequipa;
        double valor, odd;
        do{
            System.out.println("\n<<<<<Menu de Apostas>>>>");
            System.out.println("------------------------");
            menuApostas.executa();
            switch(menuApostas.getOpcao()){
                case 1:
                    System.out.print("\nNúmero do Circuito: ");
                    circuito = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Nome da Equipa: ");
                    nomeequipa = scan.nextLine();
                    System.out.print("Valor da aposta: ");
                    valor = scan.nextDouble();
                    odd = lutilizadores.getOddEquipa(nomeequipa);
                    System.out.print("Classificação: ");
                    classificacao = scan.nextInt();
                    aposta = new Aposta(circuito,nomeequipa,valor,odd,0,classificacao);
                    try {
                         ut.fazAposta(aposta, camp);
                         System.out.println("\nAposta registada!");
                    } catch (SaldoInsuficienteException | ClassificacaoErradaException | NaoExisteNomeEquipaException | NaoExisteCircuitoException | CorridaJaExecutadaException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    mmenuConfigUt(ut);
                    break;
                case 3:
                    mmenuApUt(ut,lutilizadores);
                    break;
                case 4:
                    mmenuCampU(camp);
                    break;
                default:
                    break;
            }
        }while(menuApostas.getOpcao()!=0);
    }
    
    public static void mmenuConfigUt(Utilizador ut)
    {  
        Scanner scan = new Scanner(System.in);
        String nome, pass,morada;
        Double saldo;
        do{
            System.out.println("\n<<<<<Configurações de Utilizador>>>>");
            System.out.println("------------------------------------");
            menuConfigUt.executa();
            switch(menuConfigUt.getOpcao()){
                case 1:
                    System.out.println("\n:::Dados do Utilizador "+ut.getNick()+":::");
                    System.out.println("***************************");
                    System.out.println(ut.toString());
                    break;
                case 2:
                    System.out.print("\nNome do Utilizador: ");
                    nome = scan.nextLine();
                    ut.setNome(nome);
                    System.out.println("\nAlteração guardada!");
                    break;
                case 3:
                    System.out.print("\nPassword: ");
                    pass = scan.nextLine();
                    ut.setPass(pass);
                    System.out.println("\nAlteração guardada!");
                    break;
                case 4:
                    System.out.print("\nMorada: ");
                    morada = scan.nextLine();
                    ut.setMorada(morada);
                    System.out.println("\nAlteração guardada!");
                    break;
                case 5:
                    System.out.print("\nSaldo a transferir: ");
                    saldo = scan.nextDouble();
                    try {
                        ut.transferirSaldo(saldo);
                        System.out.println("\nTransferência efectuada!");
                    }catch (LimiteTransferenciaException ex) {
                        System.out.println(ex.getMessage());
                    }          
                    break;
                default:
                    break;
            }
        }while(menuConfigUt.getOpcao()!=0);
    }
         
         
         
         
        private static void mmenuAdmin(Campeonato camp1, ListaUtilizadores lutilizadores){
            do{
                System.out.println("\n<<<<<Menu Admin>>>>");
                System.out.println("-------------------");
                menuAdmin.executa();
                switch(menuAdmin.getOpcao()){
                    case 1:
                        mmenuCampA(camp1,lutilizadores);
                        break;
                    case 2:
                        mmenuApAd(lutilizadores);
                        break;
                    case 3:
                        try{
                        camp1.guardaCampeonato("campeonato_base");
                        } catch (IOException ex) {
                            Logger.getLogger(RacingManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        break;
                }
            }while(menuAdmin.getOpcao()!=0);
        }
        
   
        private static void mmenuApAd(ListaUtilizadores lutilizadores){
            do{
                System.out.println("\n<<<<<Gestão de Apostas>>>>");
                System.out.println("-----------------------------");
                menuApAd.executa();
                switch(menuApAd.getOpcao()){
                    case 1:
                        lutilizadores.infoHistoricoTotal();
                        break;
                    case 2:
                        lutilizadores.infoApostasVigorTotal();
                        break;
                    case 3:
                        lutilizadores.printScoreboard(lutilizadores.getScoreboard());
                        break;
                    case 4:
                        lutilizadores.printListaOdds();
                        break;
                    default:
                        break;
                }
            }while(menuApAd.getOpcao()!=0);
        }
        
        private static void mmenuCampClass(Campeonato camp1){
            do{
            System.out.println("\n<<<<<Outras Classificações>>>>");
            System.out.println("------------------------------");
            menuCampClass.executa();
            switch(menuCampClass.getOpcao()){
                case 1:
                    camp1.printClassificacao();
                    break;
                case 2:
                    System.out.println("\n:::::Classificação PC1:::");
                    System.out.println("----------------------------");
                    camp1.printClassCampPC1();
                    break;
                case 3:
                    System.out.println("\n:::::Classificação PC2:::");
                    System.out.println("----------------------------");
                    camp1.printClassCampPC2();
                    break;
                case 4:
                    System.out.println("\n:::::Classificação GT:::");
                    System.out.println("----------------------------");
                    camp1.printClassCampGT();
                    break;
                case 5:
                    System.out.println("\n:::::Classificação SC:::");
                    System.out.println("----------------------------");
                    camp1.printClassCampSC();
                    break;
                case 6:
                    System.out.println("\n:::Classificação Troféu Híbrido:::");
                    System.out.println("-------------------------------------");
                    camp1.printClassCampHib();
                    break;
                case 7:
                    int num;
                    System.out.print("\nInsira o Número do Circuito: ");
                    num=ler.nextInt();
                    camp1.printClassCircuitoX(num);
                    break;
                default:
                    break;
            }
            }while(menuCampClass.getOpcao()!=0);
        }
        
        private static void mmenuCampA(Campeonato camp1,ListaUtilizadores lutilizadores){
            do{
            System.out.println("\n<<<<Gestão de Campeonato do Admin>>>>");
            System.out.println("-----------------------------------------");
            menuCampA.executa();
            switch(menuCampA.getOpcao()){
                case 1:
                    System.out.println(camp1.proxCircuito(camp1.getNcorrida()));
                    break;
                case 2:
                    String opt;
                    System.out.println("Circuito encontra-se molhado?\n(Insira \"s\" "
                            + "para confirmar ou outro caractér para continuar)");
                    opt=ler.next();
                    if(opt.equals("s") || opt.equals("S")){
                        camp1.getCircuitoX(camp1.getNcorrida()).setChuva(true);
                    }
                    camp1.simulaCorrida();
                    lutilizadores.actualizaOddsFinais(camp1.getClassificaoGeral(),camp1.getCircuitoX(camp1.getNcorrida()-1).getChuva(), camp1.getNcorrida());
                    lutilizadores.actualizaApostasVigorTotais(camp1.getCircuitoX(camp1.getNcorrida()-1));
                    lutilizadores.actualizaScoreboard();
                    camp1.getCircuitoX(camp1.getNcorrida()-1).setChuva(false);
                    break;
                case 3:
                    camp1.printClassificacao();
                    break;
                case 4:
                    mmenuCampClass(camp1);
                    break;
                case 5:
                    camp1.printEquipas();
                    break;
                case 6:
                    camp1.printCircuitos();
                    break;
                default:
                    break;
            }
            }while(menuCampA.getOpcao()!=0);
        }
        
        private static void mmenuApUt(Utilizador ut, ListaUtilizadores lut)
        {
            do{
            System.out.println("\n<<<<<Gestão de Apostas do Utilizador>>>>");
            System.out.println("---------------------------------------------");    
            menuApUt.executa();
            switch(menuApUt.getOpcao()){
                case 1:
                    ut.listaContaCorrente();
                    break;
                case 2:
                    System.out.println("\n:::Histórico de Apostas do Utilizador "+ut.getNick()+":::");
                    System.out.println("*******************************************");
                    ut.infoHistorico();
                    break;
                case 3:
                    System.out.println("\n:::Apostas em Vigor do Utilizador "+ut.getNick()+":::");
                    System.out.println("***************************************");
                    ut.infoApostasVigor();
                    break;
                case 4:
                    lut.printScoreboard(lut.getScoreboard());
                    break;
                case 5:
                    lut.printListaOdds();
                    break;
                default:
                    break;
            }
          }while(menuApUt.getOpcao()!=0);
        }
        
         private static void mmenuCampU(Campeonato camp1){
            do{
            System.out.println("\n<<<<<Gestão de Campeonato do Utilizador>>>>");
            System.out.println("---------------------------------------------");    
            menuCampU.executa();
            switch(menuCampU.getOpcao()){
                case 1:
                    System.out.println(camp1.proxCircuito(camp1.getNcorrida()));
                    break;
                case 2:
                    camp1.printClassificacao();
                    break;
                case 3:
                    mmenuCampClass(camp1);
                    break;
                case 4:
                    camp1.printEquipas();
                    break;
                case 5:
                    camp1.printCircuitos();
                    break;
                default:
                    break;
            }
            }while(menuCampU.getOpcao()!=0);
        }
    
}
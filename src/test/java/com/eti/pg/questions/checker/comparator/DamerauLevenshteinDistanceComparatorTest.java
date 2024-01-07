package com.eti.pg.questions.checker.comparator;

import org.junit.Test;

public class DamerauLevenshteinDistanceComparatorTest {

    private DamerauLevenshteinDistanceComparator comparator;

    public DamerauLevenshteinDistanceComparatorTest() {
        comparator = new DamerauLevenshteinDistanceComparator();
    }

    @Test
    public void smallAnswer_minorTypo() {
        String text1 = "oto przykładowa odpowiedź";
        String text2 = "to przykławoda odpowiedź";

        int distance = comparator.compare(text1, text2);

        System.out.println("minor_typo: "+ distance);
    }

    @Test
    public void smallAnswer_minorMistake() {
        String text1 = "public static void int Main(string[] args)\n" +
                "{\n" +
                "Console.WriteLine(\"HelloWorld!\");" +
                "}\n";
        String text2 = "public static void int Main(string arg)\n" +
                "{\n" +
                "Console.WriteLine(\"Hello there!\");" +
                "}\n";

        int distance = comparator.compare(text1, text2);

        System.out.println("minor_mistake: " + distance);
    }

    @Test
    public void compareTest_longAnswer_correct() {
        String text1 = "d)\n" +
                "Projekt systemu - proces definiowania architektury systemu oraz jej elementów aby zostały spełnione cele i wymagania postawione przed tym systemem.\n" +
                "\n" +
                "e)\n" +
                "Analiza systemowa odpowiada na pytanie CO?\n" +
                "Projekt systemu odpowiada na pytanie JAK?\n" +
                "Analiza ukierunkowana jest na maksymalizację korzyści biznesowych klienta, identyfikuje i dokumentuje wymagania względem systemu.\n" +
                "Projekt systemu zawiera decyzje odnośnie docelowej jego architektury, użytych technologii oraz uszczegóławia model logiczny z fazy analizy.\n" +
                "\n" +
                "f)\n" +
                "Określenie architektury: Wyodrębnienie warstw systemu, podział na podsystemy, określenie interfejsów między warstwami i podsystemami.\n" +
                "\n" +
                "Podział na podsystemy: Podsystem realizuje określony zestaw funkcji, reprezentuje behawioralną jednostkę w fizycznym systemie.\n" +
                "Decyzje o fizycznym rozmieszczeniu systemu: Jak system będzie rozmieszczony na docelowym sprzęcie. Stosuje się do nich diagramy wdrożenia.\n" +
                "Identyfikacja i obsługa dostępu do zasobów globalnych: Zasoby takie jak pliki i dane w bazie danych. Są różne metody synchronizujące do nich dostęp takie jak guardian objects (wolne ale bezpieczne) czy locks (szybkie ale mogą spowodować deadlock).\n" +
                "Obsługa warunków granicznych:\n" +
                "-> Inicjalizacja - Przejście od stanu spoczynku do ustabilizowanego stanu pracy systemu.\n" +
                "-> Zakończenie - Przejście ze stanu pracy do stanu spoczynku. W jego skład wchodzą: zwolnienie zasobów, usunięcie obiektów nietrwałych, systematyczne kończenie zależnych od siebie zadań\n" +
                "-> Upadek - nieoczekiwane zakończenie pracy systemu. W miarę możliwości należy opisać jego przyczyny oraz stan systemu.\n" +
                "\n" +
                "Wybranie podejścia do zarządzania pojemnikami danych: Mogą one służyć jako interfejs pomiędzy systemami lub jako zasoby umożliwiające współużytkowanie informacji.\n" +
                "Decyzje co do stylu projektowania i wypracowanie rozwiązań kompromisowych.\n";
        String text2 = "d)\n" +
                "Projekt systemu systemu polega na określaniu struktury systemu oraz jego części w taki sposób, aby spełnić założone cele i oczekiwania.\n" +
                "\n" +
                "e)\n" +
                "W analizie systemowej pytamy \"CO?\". Natomiast przy tworzeniu systemu zastanawiamy się \"JAK?\". Analiza skupia się na maksymalizacji korzyści dla biznesu klienta i precyzyjnym zdefiniowaniu wymagań dla systemu. W projekcie systemu podejmuje się decyzje dotyczące struktury, technologii i rozwinięcia koncepcji logicznej z analizy.\n" +
                "\n" +
                "f)\n" +
                "Określenie architektury: Obejmuje to wyodrębnienie warstw systemu, podział na podsystemy oraz ustalenie interfejsów pomiędzy nimi.\n" +
                "\n" +
                "Podział na podsystemy: Każdy podsystem realizuje konkretny zestaw funkcji i stanowi odrębną jednostkę w całym systemie.\n" +
                "\n" +
                "Decyzje dotyczące rozmieszczenia systemu: Odnoszą się do tego, jak system zostanie ulokowany na konkretnym sprzęcie, często przedstawiane za pomocą diagramów.\n" +
                "\n" +
                "Zarządzanie dostępem do zasobów globalnych: Dotyczy to zasobów, takich jak pliki czy dane w bazie danych. Istnieją różne metody kontroli dostępu, takie jak obiekty strażnikowe (bezpieczne, ale wolniejsze) czy blokady (szybkie, ale mogące prowadzić do blokowania).\n" +
                "\n" +
                "Obsługa sytuacji wyjątkowych:\n" +
                "-> Inicjalizacja: Przejście systemu ze stanu spoczynku do pełnej aktywności.\n" +
                "-> Zakończenie: Powrót systemu do stanu spoczynku, obejmuje m.in. zwalnianie zasobów i kończenie zależnych zadań.\n" +
                "-> Upadek: Nagłe zakończenie działania systemu, które powinno być dokładnie udokumentowane.\n" +
                "\n" +
                "Wybór strategii zarządzania danymi: Dane mogą pełnić rolę interfejsu między systemami lub być współdzielonymi zasobami.\n" +
                "\n" +
                "Decyzje dotyczące stylu projektowania i szukanie kompromisowych rozwiązań.";

        double similarity = (double)comparator.compare(text1, text2) / (double)text1.length();

        System.out.println("compareTest_longAnswer_correct: " + similarity);
    }

    @Test
    public void compareTest_longAnswer_incorrect() {
        String text1 = "d)\n" +
                "Projekt systemu - proces definiowania architektury systemu oraz jej elementów aby zostały spełnione cele i wymagania postawione przed tym systemem.\n" +
                "\n" +
                "e)\n" +
                "Analiza systemowa odpowiada na pytanie CO?\n" +
                "Projekt systemu odpowiada na pytanie JAK?\n" +
                "Analiza ukierunkowana jest na maksymalizację korzyści biznesowych klienta, identyfikuje i dokumentuje wymagania względem systemu.\n" +
                "Projekt systemu zawiera decyzje odnośnie docelowej jego architektury, użytych technologii oraz uszczegóławia model logiczny z fazy analizy.\n" +
                "\n" +
                "f)\n" +
                "Określenie architektury: Wyodrębnienie warstw systemu, podział na podsystemy, określenie interfejsów między warstwami i podsystemami.\n" +
                "\n" +
                "Podział na podsystemy: Podsystem realizuje określony zestaw funkcji, reprezentuje behawioralną jednostkę w fizycznym systemie.\n" +
                "Decyzje o fizycznym rozmieszczeniu systemu: Jak system będzie rozmieszczony na docelowym sprzęcie. Stosuje się do nich diagramy wdrożenia.\n" +
                "Identyfikacja i obsługa dostępu do zasobów globalnych: Zasoby takie jak pliki i dane w bazie danych. Są różne metody synchronizujące do nich dostęp takie jak guardian objects (wolne ale bezpieczne) czy locks (szybkie ale mogą spowodować deadlock).\n" +
                "Obsługa warunków granicznych:\n" +
                "-> Inicjalizacja - Przejście od stanu spoczynku do ustabilizowanego stanu pracy systemu.\n" +
                "-> Zakończenie - Przejście ze stanu pracy do stanu spoczynku. W jego skład wchodzą: zwolnienie zasobów, usunięcie obiektów nietrwałych, systematyczne kończenie zależnych od siebie zadań\n" +
                "-> Upadek - nieoczekiwane zakończenie pracy systemu. W miarę możliwości należy opisać jego przyczyny oraz stan systemu.\n" +
                "\n" +
                "Wybranie podejścia do zarządzania pojemnikami danych: Mogą one służyć jako interfejs pomiędzy systemami lub jako zasoby umożliwiające współużytkowanie informacji.\n" +
                "Decyzje co do stylu projektowania i wypracowanie rozwiązań kompromisowych.\n";
        String text2 = "d)\n" +
                "Projekt systemu - proces projektowania systemu tak żeby nie działał.\n" +
                "\n" +
                "e)\n" +
                "Analiza systemowa odpowiada na pytanie co ja to wogóle robię?\n" +
                "Projekt systemu to sam nie wiem po co jest, nie chce mi się dalej pisać, daj pan tróję i spadam naura.";

        double similarity = (double)comparator.compare(text1, text2) / (double)text1.length();

        System.out.println("compareTest_longAnswer_incorrect: " + similarity);
    }

    @Test
    public void compareTest_longAnswer_partiallyCorrect() {
        String text1 = "d)\n" +
                "Projekt systemu - proces definiowania architektury systemu oraz jej elementów aby zostały spełnione cele i wymagania postawione przed tym systemem.\n" +
                "\n" +
                "e)\n" +
                "Analiza systemowa odpowiada na pytanie CO?\n" +
                "Projekt systemu odpowiada na pytanie JAK?\n" +
                "Analiza ukierunkowana jest na maksymalizację korzyści biznesowych klienta, identyfikuje i dokumentuje wymagania względem systemu.\n" +
                "Projekt systemu zawiera decyzje odnośnie docelowej jego architektury, użytych technologii oraz uszczegóławia model logiczny z fazy analizy.\n" +
                "\n" +
                "f)\n" +
                "Określenie architektury: Wyodrębnienie warstw systemu, podział na podsystemy, określenie interfejsów między warstwami i podsystemami.\n" +
                "\n" +
                "Podział na podsystemy: Podsystem realizuje określony zestaw funkcji, reprezentuje behawioralną jednostkę w fizycznym systemie.\n" +
                "Decyzje o fizycznym rozmieszczeniu systemu: Jak system będzie rozmieszczony na docelowym sprzęcie. Stosuje się do nich diagramy wdrożenia.\n" +
                "Identyfikacja i obsługa dostępu do zasobów globalnych: Zasoby takie jak pliki i dane w bazie danych. Są różne metody synchronizujące do nich dostęp takie jak guardian objects (wolne ale bezpieczne) czy locks (szybkie ale mogą spowodować deadlock).\n" +
                "Obsługa warunków granicznych:\n" +
                "-> Inicjalizacja - Przejście od stanu spoczynku do ustabilizowanego stanu pracy systemu.\n" +
                "-> Zakończenie - Przejście ze stanu pracy do stanu spoczynku. W jego skład wchodzą: zwolnienie zasobów, usunięcie obiektów nietrwałych, systematyczne kończenie zależnych od siebie zadań\n" +
                "-> Upadek - nieoczekiwane zakończenie pracy systemu. W miarę możliwości należy opisać jego przyczyny oraz stan systemu.\n" +
                "\n" +
                "Wybranie podejścia do zarządzania pojemnikami danych: Mogą one służyć jako interfejs pomiędzy systemami lub jako zasoby umożliwiające współużytkowanie informacji.\n" +
                "Decyzje co do stylu projektowania i wypracowanie rozwiązań kompromisowych.\n";
        String text2 = "\n" +
                "d)\n" +
                "Projekt systemu - to proces określania struktury systemu oraz jego komponentów w celu realizacji założonych celów i oczekiwań.\n" +
                "\n" +
                "e)\n" +
                "Podczas analizy systemowej zastanawiamy się, \"CO?\". Natomiast podczas tworzenia systemu pytamy \"JAK?\". Analiza koncentruje się na optymalizacji korzyści dla biznesu klienta i sprecyzowaniu wymagań dotyczących systemu. W projekcie systemu podejmowane są decyzje dotyczące struktury, technologii oraz rozwinięcia koncepcji logicznej z analizy.";

        double similarity = (double)comparator.compare(text1, text2) / (double) text1.length();

        System.out.println("compareTest_longAnswer_partiallyCorrect: " + similarity);
    }

}

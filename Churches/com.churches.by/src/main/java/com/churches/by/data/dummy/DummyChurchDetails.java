package com.churches.by.data.dummy;

import android.graphics.BitmapFactory;

import com.churches.by.CAppliation;
import com.churches.by.R;
import com.churches.by.data.model.ChurchDetails;
import com.churches.by.data.model.ScheduleItem;

import java.util.Arrays;

public class DummyChurchDetails {

    public static ChurchDetails Borisov = new ChurchDetails(
            DummyChurch.Borisov,
            BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.church_borisov2),
            Arrays.asList(DummyChurchEvent.Dummy, DummyChurchEvent.Dummy, DummyChurchEvent.Dummy),
            Arrays.asList(
                    new ScheduleItem("нядзеля: 09.00, 11.00 (польск.), 16.30 (для моладзі), 18.00"),
                    new ScheduleItem("будні: 18.00, сб — 09.00, 18.00")),
            "Парафія існуе з 1642 г.; касцёл 1806–1830 гг. быў зачынены ў 1937 г., парафія адроджана ў 1989 г., касцёл адноўлены ў 1990 г.");

    public static ChurchDetails Katedra = new ChurchDetails(
            DummyChurch.Katedra,
            BitmapFactory.decodeResource(CAppliation.instance().getResources(), R.drawable.katedra_big),
            Arrays.asList(DummyChurchEvent.Dummy, DummyChurchEvent.Dummy, DummyChurchEvent.Dummy),
            Arrays.asList(new ScheduleItem("нядзеля: 08.15, 10.00, 11.30, 13.00, 18.30"),
                    new ScheduleItem("будні: 07.15, 08.00, 18.30"),
                    new ScheduleItem("урачыстасці: 07.15, 8.00, 12.00, 18.30")),
            "31 лiпеня 1700 – заснаванне касцёла айцамi езуiтамi.\n" +
                    "\n" +
                    "16 сакавiка 1710 – кансэкрацыя касцёла, якую здзейснiў Вiленскi бiскуп Канстанты Казiмiр Бжастоўскi.\n" +
                    "\n" +
                    "1773 – касцёл закону езуiтаў стаў парафiяльным.\n" +
                    "\n" +
                    "28 красавiка 1798 – святыні нададзена годнасць Катэдральнага касцёла.\n" +
                    "\n" +
                    "З красавiка 1905 – пэўны час пробашчам Катэдры быў ксёндз Зыгмунт Лазiнскi.\n" +
                    "\n" +
                    "1948 – касцёл адабраны ў вернікаў i перададзены пад \"Дом фiзкультуры\".\n" +
                    "\n" +
                    "1951 – касцёл цалкам спустошаны i перабудаваны.\n" +
                    "\n" +
                    "9 снежня 1993 – паводле пастановы Савета Мiнiстраў Рэспублiкi Беларусь будынак Мiнскай катэдры быў вернуты католiкам.\n" +
                    "\n" +
                    "5 лютага 1994 – адбылося папярэдняе пасвячэнне святыні i на яе франтоне быў устаноўлены крыж.\n" +
                    "\n" +
                    "З 16 жнiўня 1994 да 16 кастрычнiка 1997 – вялiся рэстаўрацыйныя работы.\n" +
                    "\n" +
                    "28 верасня 1996 – у Мiнскай катэдры Імя Найсвяцейшай Марыi Панны адбылася iнаугурацыя Сiноду Мiнска-Магiлёўскай архiдыяцэзii i Пiнскай дыяцэзii.\n" +
                    "\n" +
                    "21 кастрычніка 1997 – рэкансэкрацыя Мiнскай катэдры Імя Найсвяцейшай Марыi Панны.\n" +
                    "\n" +
                    "10 снежня 2005 – урачыстае асвячэнне узноўленага галоўнага алтара, каранацыя абраза Імя Марыі Беззаганна Зачатай, асвячэнне новых арганаў.");

}

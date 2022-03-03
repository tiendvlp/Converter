package com.prm.converter.view.convert;

public interface MainScreenComponentProvider {
    public interface ConvertInputScreen {

    }

    public interface ConvertOutputScreen {
        void showOutput(double value);
    }

    ConvertInputScreen getConvertInputScreen ();
    ConvertOutputScreen getConvertOutputScreen ();
}

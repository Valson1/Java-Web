package by.epam.lab.services;

import by.epam.lab.beans.marks.DecimalMark;
import by.epam.lab.beans.marks.HalfMark;
import by.epam.lab.beans.marks.Mark;
import by.epam.lab.interfaces.MarkFormat;

public enum MarkKind {
    MARK {
	@Override
	public MarkFormat getMarkFromFactory(String mark) {
	    return new Mark(mark);
	}

	@Override
	public MarkFormat getMarkFromFactory(int mark) {
	    return new Mark(mark);
	}

	@Override
	public MarkFormat getMarkFromFactory(double mark) {
	    return new Mark(mark);
	}
    },
    HALF_MARK {
	@Override
	public MarkFormat getMarkFromFactory(String mark) {
	    return new HalfMark(mark);
	}

	@Override
	public MarkFormat getMarkFromFactory(int mark) {
	    return new HalfMark(mark);
	}

	@Override
	public MarkFormat getMarkFromFactory(double mark) {
	    return new HalfMark(mark);
	}
    },
    DECIMAL_MARK {
	@Override
	public MarkFormat getMarkFromFactory(String mark) {
	    return new DecimalMark(mark);
	}

	@Override
	public MarkFormat getMarkFromFactory(int mark) {
	    return new DecimalMark(mark);
	}

	@Override
	public MarkFormat getMarkFromFactory(double mark) {
	    return new DecimalMark(mark);
	}
    };

    public abstract MarkFormat getMarkFromFactory(String mark);

    public abstract MarkFormat getMarkFromFactory(int mark);

    public abstract MarkFormat getMarkFromFactory(double mark);
}

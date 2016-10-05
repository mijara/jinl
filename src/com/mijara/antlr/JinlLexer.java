// Generated from /Users/mijara/Projects/jinl/antlr/Jinl.g4 by ANTLR 4.5.3

package com.mijara.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JinlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, END=5, VAR=6, RETURN=7, FUNCTION_NAME=8, 
		STRONG_NAME=9, IDENTIFIER=10, INTEGER=11, WHITESPACE=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "END", "VAR", "RETURN", "FUNCTION_NAME", 
		"STRONG_NAME", "IDENTIFIER", "INTEGER", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "':'", "'='", "'End'", "'var'", "'return'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "END", "VAR", "RETURN", "FUNCTION_NAME", 
		"STRONG_NAME", "IDENTIFIER", "INTEGER", "WHITESPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JinlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Jinl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 7:
			FUNCTION_NAME_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void FUNCTION_NAME_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 setText(getText().substring(1)); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16Q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\6\t\64\n\t\r\t\16\t\65"+
		"\3\t\3\t\3\t\3\n\3\n\6\n=\n\n\r\n\16\n>\3\13\6\13B\n\13\r\13\16\13C\3"+
		"\f\6\fG\n\f\r\f\16\fH\3\r\6\rL\n\r\r\r\16\rM\3\r\3\r\2\2\16\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\3\2\7\3\2C\\\5\2\62;C"+
		"\\c|\4\2C\\c|\3\2\62;\5\2\13\f\16\17\"\"U\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5"+
		"\35\3\2\2\2\7\37\3\2\2\2\t!\3\2\2\2\13#\3\2\2\2\r\'\3\2\2\2\17+\3\2\2"+
		"\2\21\63\3\2\2\2\23:\3\2\2\2\25A\3\2\2\2\27F\3\2\2\2\31K\3\2\2\2\33\34"+
		"\7*\2\2\34\4\3\2\2\2\35\36\7+\2\2\36\6\3\2\2\2\37 \7<\2\2 \b\3\2\2\2!"+
		"\"\7?\2\2\"\n\3\2\2\2#$\7G\2\2$%\7p\2\2%&\7f\2\2&\f\3\2\2\2\'(\7x\2\2"+
		"()\7c\2\2)*\7t\2\2*\16\3\2\2\2+,\7t\2\2,-\7g\2\2-.\7v\2\2./\7w\2\2/\60"+
		"\7t\2\2\60\61\7p\2\2\61\20\3\2\2\2\62\64\7B\2\2\63\62\3\2\2\2\64\65\3"+
		"\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\678\5\23\n\289\b\t\2"+
		"\29\22\3\2\2\2:<\t\2\2\2;=\t\3\2\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2"+
		"\2\2?\24\3\2\2\2@B\t\4\2\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\26"+
		"\3\2\2\2EG\t\5\2\2FE\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2I\30\3\2\2\2"+
		"JL\t\6\2\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\b\r\3\2"+
		"P\32\3\2\2\2\b\2\65>CHM\4\3\t\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
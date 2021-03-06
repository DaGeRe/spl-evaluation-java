/* FormulaParser.java */
/* Generated By:JavaCC: Do not edit this line. FormulaParser.java */
package cz.cuni.mff.d3s.spl.formula;

import java.util.*;

import cz.cuni.mff.d3s.spl.Formula;
import cz.cuni.mff.d3s.spl.interpretation.KindergartenInterpretation;
import cz.cuni.mff.d3s.spl.Result;

class FormulaParser implements FormulaParserConstants {
        public static Formula parse(String formula) throws FormulaParsingException {
                try {
                        FormulaParser parser = new FormulaParser(formula);
                        Formula parsedFormula = parser.splFormula();
                        parsedFormula.setInterpretation(Defaults.getInterpretation());
                        return parsedFormula;
                } catch (TokenMgrError e) {
                        throw new FormulaParsingException(e, formula);
                } catch (ParseException e) {
                        throw new FormulaParsingException(e, formula);
                }
        }

        public FormulaParser(String formula) {
                this(new java.io.BufferedReader(new java.io.StringReader(formula)));
        }

  final public Formula splFormula() throws ParseException {Formula tmp;
    tmp = logicFormulaImply();
{if ("" != null) return tmp;}
    throw new Error("Missing return statement in function");
  }

  final public Formula logicFormulaImply() throws ParseException {List<Formula> parts = new LinkedList<Formula>();
        Formula tmp;
    tmp = logicFormulaOr();
parts.add(tmp);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LOGIC_IMPLY:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(LOGIC_IMPLY);
      tmp = logicFormulaOr();
parts.add(tmp);
    }
Formula result = parts.get(0);
                for (int i = 1; i < parts.size(); i++) {
                        result = new LogicImply(result, parts.get(i));
                }
                {if ("" != null) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Formula logicFormulaOr() throws ParseException {List<Formula> parts = new LinkedList<Formula>();
        Formula tmp = null;
    tmp = logicFormulaAnd();
parts.add(tmp);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LOGIC_OR:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(LOGIC_OR);
      tmp = logicFormulaAnd();
parts.add(tmp);
    }
Formula result = parts.get(0);
                for (int i = 1; i < parts.size(); i++) {
                        result = new LogicOr(result, parts.get(i));
                }
                {if ("" != null) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Formula logicFormulaAnd() throws ParseException {List<Formula> parts = new LinkedList<Formula>();
        Formula tmp;
    tmp = logicFormulaAtomic();
parts.add(tmp);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LOGIC_AND:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      jj_consume_token(LOGIC_AND);
      tmp = logicFormulaAtomic();
parts.add(tmp);
    }
Formula result = parts.get(0);
                for (int i = 1; i < parts.size(); i++) {
                        result = new LogicAnd(result, parts.get(i));
                }
                {if ("" != null) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Formula logicFormulaAtomic() throws ParseException {Formula atomic;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENTIFIER:
    case REAL:
    case REAL2:
    case INT:{
      atomic = plainComparison();
{if ("" != null) return atomic;}
      break;
      }
    case LEFT_PAREN:{
      jj_consume_token(LEFT_PAREN);
      atomic = splFormula();
      jj_consume_token(RIGHT_PAREN);
{if ("" != null) return atomic;}
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Formula plainComparison() throws ParseException {String leftName;
        double leftConst;
        String rightName;
        double rightConst;
        Comparison.Operator op;
    if (jj_2_1(3)) {
      leftName = sourceName();
      op = comparisonOperator();
      rightName = sourceName();
{if ("" != null) return new Comparison(leftName, rightName, op);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case REAL:
      case REAL2:
      case INT:{
        leftConst = constantSource();
        op = inverseComparisonOperator();
        rightName = sourceName();
{if ("" != null) return new ComparisonWithConst(rightName, leftConst, op);}
        break;
        }
      case IDENTIFIER:{
        leftName = sourceName();
        op = comparisonOperator();
        rightConst = constantSource();
{if ("" != null) return new ComparisonWithConst(leftName, rightConst, op);}
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String sourceName() throws ParseException {Token name;
    name = jj_consume_token(IDENTIFIER);
{if ("" != null) return name.image;}
    throw new Error("Missing return statement in function");
  }

  final public double constantSource() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:{
      t = jj_consume_token(INT);
{if ("" != null) return Integer.valueOf(t.image);}
      break;
      }
    case REAL:{
      t = jj_consume_token(REAL);
{if ("" != null) return Double.valueOf(t.image);}
      break;
      }
    case REAL2:{
      t = jj_consume_token(REAL2);
{if ("" != null) return Double.valueOf("0" + t.image);}
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Comparison.Operator comparisonOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case CMP_GT:{
      jj_consume_token(CMP_GT);
{if ("" != null) return Comparison.Operator.GT;}
      break;
      }
    case CMP_LT:{
      jj_consume_token(CMP_LT);
{if ("" != null) return Comparison.Operator.LT;}
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Comparison.Operator inverseComparisonOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case CMP_LT:{
      jj_consume_token(CMP_LT);
{if ("" != null) return Comparison.Operator.GT;}
      break;
      }
    case CMP_GT:{
      jj_consume_token(CMP_GT);
{if ("" != null) return Comparison.Operator.LT;}
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_3R_7()
 {
    if (jj_scan_token(CMP_LT)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_4()) return true;
    if (jj_3R_5()) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3R_6()
 {
    if (jj_scan_token(CMP_GT)) return true;
    return false;
  }

  private boolean jj_3R_5()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_6()) {
    jj_scanpos = xsp;
    if (jj_3R_7()) return true;
    }
    return false;
  }

  private boolean jj_3R_4()
 {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public FormulaParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2000,0x1000,0x800,0x41e0,0x1e0,0x1c0,0x600,0x600,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[1];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public FormulaParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public FormulaParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new FormulaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public FormulaParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new FormulaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public FormulaParser(FormulaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(FormulaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[16];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 16; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}

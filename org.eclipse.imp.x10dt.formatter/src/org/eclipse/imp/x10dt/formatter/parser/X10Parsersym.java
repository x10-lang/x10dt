
// This is the grammar for parsing formatting patterns for  the X10 language.

//#line 18 "/Users/beth/ews/x10dt17-apr-34/org.eclipse.imp.x10dt.formatter/src/org/eclipse/imp/x10dt/formatter/parser/x10.g"
//
// Licensed Material
// (C) Copyright IBM Corp, 2006
//

////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2007 IBM Corporation.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//    Philippe Charles (pcharles@us.ibm.com) - initial API and implementation

////////////////////////////////////////////////////////////////////////////////

package org.eclipse.imp.x10dt.formatter.parser;

public interface X10Parsersym {
    public final static int
      TK_IntegerLiteral = 82,
      TK_LongLiteral = 83,
      TK_FloatingPointLiteral = 84,
      TK_DoubleLiteral = 85,
      TK_CharacterLiteral = 86,
      TK_StringLiteral = 87,
      TK_MINUS_MINUS = 93,
      TK_OR = 131,
      TK_MINUS = 96,
      TK_MINUS_EQUAL = 148,
      TK_NOT = 98,
      TK_NOT_EQUAL = 132,
      TK_REMAINDER = 136,
      TK_REMAINDER_EQUAL = 149,
      TK_AND = 133,
      TK_AND_AND = 137,
      TK_AND_EQUAL = 150,
      TK_LPAREN = 1,
      TK_RPAREN = 77,
      TK_MULTIPLY = 134,
      TK_MULTIPLY_EQUAL = 151,
      TK_COMMA = 91,
      TK_DOT = 101,
      TK_DIVIDE = 138,
      TK_DIVIDE_EQUAL = 152,
      TK_COLON = 105,
      TK_SEMICOLON = 79,
      TK_QUESTION = 144,
      TK_AT = 5,
      TK_LBRACKET = 76,
      TK_RBRACKET = 122,
      TK_XOR = 135,
      TK_XOR_EQUAL = 153,
      TK_LBRACE = 92,
      TK_OR_OR = 142,
      TK_OR_EQUAL = 154,
      TK_RBRACE = 115,
      TK_TWIDDLE = 100,
      TK_PLUS = 97,
      TK_PLUS_PLUS = 94,
      TK_PLUS_EQUAL = 155,
      TK_LESS = 124,
      TK_LEFT_SHIFT = 139,
      TK_LEFT_SHIFT_EQUAL = 156,
      TK_RIGHT_SHIFT = 140,
      TK_RIGHT_SHIFT_EQUAL = 157,
      TK_UNSIGNED_RIGHT_SHIFT = 141,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 158,
      TK_LESS_EQUAL = 125,
      TK_EQUAL = 104,
      TK_EQUAL_EQUAL = 111,
      TK_GREATER = 126,
      TK_GREATER_EQUAL = 127,
      TK_ELLIPSIS = 170,
      TK_RANGE = 163,
      TK_ARROW = 99,
      TK_DARROW = 145,
      TK_SUBTYPE = 117,
      TK_SUPERTYPE = 118,
      TK_abstract = 7,
      TK_as = 28,
      TK_at = 46,
      TK_assert = 47,
      TK_async = 48,
      TK_ateach = 37,
      TK_atomic = 18,
      TK_await = 49,
      TK_break = 50,
      TK_case = 26,
      TK_catch = 31,
      TK_class = 14,
      TK_clocked = 32,
      TK_const = 51,
      TK_continue = 52,
      TK_def = 15,
      TK_default = 27,
      TK_do = 38,
      TK_else = 39,
      TK_extends = 33,
      TK_extern = 19,
      TK_false = 53,
      TK_final = 8,
      TK_finally = 34,
      TK_finish = 54,
      TK_for = 40,
      TK_foreach = 41,
      TK_future = 108,
      TK_in = 119,
      TK_goto = 55,
      TK_has = 56,
      TK_here = 57,
      TK_if = 58,
      TK_implements = 42,
      TK_import = 35,
      TK_incomplete = 20,
      TK_instanceof = 29,
      TK_interface = 11,
      TK_local = 21,
      TK_native = 16,
      TK_new = 59,
      TK_next = 60,
      TK_nonblocking = 22,
      TK_now = 61,
      TK_null = 62,
      TK_or = 43,
      TK_operator = 107,
      TK_package = 44,
      TK_private = 2,
      TK_property = 17,
      TK_protected = 3,
      TK_public = 4,
      TK_return = 63,
      TK_safe = 12,
      TK_self = 64,
      TK_sequential = 23,
      TK_shared = 30,
      TK_static = 6,
      TK_strictfp = 10,
      TK_super = 80,
      TK_switch = 65,
      TK_synchronized = 171,
      TK_this = 78,
      TK_throw = 66,
      TK_throws = 45,
      TK_transient = 24,
      TK_true = 67,
      TK_try = 68,
      TK_type = 9,
      TK_unsafe = 69,
      TK_val = 70,
      TK_value = 13,
      TK_var = 71,
      TK_volatile = 25,
      TK_when = 72,
      TK_while = 36,
      TK_EOF_TOKEN = 109,
      TK_IDENTIFIER = 73,
      TK_SlComment = 172,
      TK_MlComment = 173,
      TK_DocComment = 174,
      TK_METAVARIABLE_PackageName = 164,
      TK_METAVARIABLE_X10ClassModifier = 175,
      TK_METAVARIABLE_X10ClassModifiers = 176,
      TK_METAVARIABLE_Property = 165,
      TK_METAVARIABLE_Properties = 166,
      TK_METAVARIABLE_identifier = 74,
      TK_METAVARIABLE_Type = 102,
      TK_METAVARIABLE_ConstExpression = 177,
      TK_METAVARIABLE_DepParameterExp = 178,
      TK_METAVARIABLE_MethodModifier = 106,
      TK_METAVARIABLE_Statement = 112,
      TK_METAVARIABLE_ClockList = 160,
      TK_METAVARIABLE_Expression = 75,
      TK_METAVARIABLE_FieldModifier = 116,
      TK_METAVARIABLE_FieldModifiers = 123,
      TK_METAVARIABLE_ArgumentList = 146,
      TK_METAVARIABLE_Object = 179,
      TK_METAVARIABLE_TypeNode = 180,
      TK_METAVARIABLE_PackageNode = 181,
      TK_METAVARIABLE_Import = 182,
      TK_METAVARIABLE_ClassDecl = 183,
      TK_METAVARIABLE_ClassBodyDeclaration = 128,
      TK_METAVARIABLE_ClassBodyDeclarations = 161,
      TK_METAVARIABLE_TypeDeclaration = 159,
      TK_METAVARIABLE_TypeDeclarations = 162,
      TK_METAVARIABLE_BlockStatement = 129,
      TK_METAVARIABLE_BlockStatements = 143,
      TK_METAVARIABLE_ClassName = 88,
      TK_METAVARIABLE_TypeName = 81,
      TK_METAVARIABLE_TypeArgument = 184,
      TK_METAVARIABLE_TypeArgumentList = 167,
      TK_METAVARIABLE_Primary = 89,
      TK_METAVARIABLE_AmbiguousName = 90,
      TK_METAVARIABLE_WhenStatement = 113,
      TK_METAVARIABLE_FormalParameterList = 147,
      TK_METAVARIABLE_LastFormalParameter = 185,
      TK_METAVARIABLE_FormalParameters = 120,
      TK_METAVARIABLE_FormalParameter = 130,
      TK_METAVARIABLE_StatementExpression = 110,
      TK_ErrorId = 121,
      TK_PathType = 103,
      TK_any = 168,
      TK_current = 169,
      TK_SynchronizedStatement = 114,
      TK_ObjectKind = 186,
      TK_ArrayInitailizer = 95,
      TK_ERROR_TOKEN = 187;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "private",
                 "protected",
                 "public",
                 "AT",
                 "static",
                 "abstract",
                 "final",
                 "type",
                 "strictfp",
                 "interface",
                 "safe",
                 "value",
                 "class",
                 "def",
                 "native",
                 "property",
                 "atomic",
                 "extern",
                 "incomplete",
                 "local",
                 "nonblocking",
                 "sequential",
                 "transient",
                 "volatile",
                 "case",
                 "default",
                 "as",
                 "instanceof",
                 "shared",
                 "catch",
                 "clocked",
                 "extends",
                 "finally",
                 "import",
                 "while",
                 "ateach",
                 "do",
                 "else",
                 "for",
                 "foreach",
                 "implements",
                 "or",
                 "package",
                 "throws",
                 "at",
                 "assert",
                 "async",
                 "await",
                 "break",
                 "const",
                 "continue",
                 "false",
                 "finish",
                 "goto",
                 "has",
                 "here",
                 "if",
                 "new",
                 "next",
                 "now",
                 "null",
                 "return",
                 "self",
                 "switch",
                 "throw",
                 "true",
                 "try",
                 "unsafe",
                 "val",
                 "var",
                 "when",
                 "IDENTIFIER",
                 "METAVARIABLE_identifier",
                 "METAVARIABLE_Expression",
                 "LBRACKET",
                 "RPAREN",
                 "this",
                 "SEMICOLON",
                 "super",
                 "METAVARIABLE_TypeName",
                 "IntegerLiteral",
                 "LongLiteral",
                 "FloatingPointLiteral",
                 "DoubleLiteral",
                 "CharacterLiteral",
                 "StringLiteral",
                 "METAVARIABLE_ClassName",
                 "METAVARIABLE_Primary",
                 "METAVARIABLE_AmbiguousName",
                 "COMMA",
                 "LBRACE",
                 "MINUS_MINUS",
                 "PLUS_PLUS",
                 "ArrayInitailizer",
                 "MINUS",
                 "PLUS",
                 "NOT",
                 "ARROW",
                 "TWIDDLE",
                 "DOT",
                 "METAVARIABLE_Type",
                 "PathType",
                 "EQUAL",
                 "COLON",
                 "METAVARIABLE_MethodModifier",
                 "operator",
                 "future",
                 "EOF_TOKEN",
                 "METAVARIABLE_StatementExpression",
                 "EQUAL_EQUAL",
                 "METAVARIABLE_Statement",
                 "METAVARIABLE_WhenStatement",
                 "SynchronizedStatement",
                 "RBRACE",
                 "METAVARIABLE_FieldModifier",
                 "SUBTYPE",
                 "SUPERTYPE",
                 "in",
                 "METAVARIABLE_FormalParameters",
                 "ErrorId",
                 "RBRACKET",
                 "METAVARIABLE_FieldModifiers",
                 "LESS",
                 "LESS_EQUAL",
                 "GREATER",
                 "GREATER_EQUAL",
                 "METAVARIABLE_ClassBodyDeclaration",
                 "METAVARIABLE_BlockStatement",
                 "METAVARIABLE_FormalParameter",
                 "OR",
                 "NOT_EQUAL",
                 "AND",
                 "MULTIPLY",
                 "XOR",
                 "REMAINDER",
                 "AND_AND",
                 "DIVIDE",
                 "LEFT_SHIFT",
                 "RIGHT_SHIFT",
                 "UNSIGNED_RIGHT_SHIFT",
                 "OR_OR",
                 "METAVARIABLE_BlockStatements",
                 "QUESTION",
                 "DARROW",
                 "METAVARIABLE_ArgumentList",
                 "METAVARIABLE_FormalParameterList",
                 "MINUS_EQUAL",
                 "REMAINDER_EQUAL",
                 "AND_EQUAL",
                 "MULTIPLY_EQUAL",
                 "DIVIDE_EQUAL",
                 "XOR_EQUAL",
                 "OR_EQUAL",
                 "PLUS_EQUAL",
                 "LEFT_SHIFT_EQUAL",
                 "RIGHT_SHIFT_EQUAL",
                 "UNSIGNED_RIGHT_SHIFT_EQUAL",
                 "METAVARIABLE_TypeDeclaration",
                 "METAVARIABLE_ClockList",
                 "METAVARIABLE_ClassBodyDeclarations",
                 "METAVARIABLE_TypeDeclarations",
                 "RANGE",
                 "METAVARIABLE_PackageName",
                 "METAVARIABLE_Property",
                 "METAVARIABLE_Properties",
                 "METAVARIABLE_TypeArgumentList",
                 "any",
                 "current",
                 "ELLIPSIS",
                 "synchronized",
                 "SlComment",
                 "MlComment",
                 "DocComment",
                 "METAVARIABLE_X10ClassModifier",
                 "METAVARIABLE_X10ClassModifiers",
                 "METAVARIABLE_ConstExpression",
                 "METAVARIABLE_DepParameterExp",
                 "METAVARIABLE_Object",
                 "METAVARIABLE_TypeNode",
                 "METAVARIABLE_PackageNode",
                 "METAVARIABLE_Import",
                 "METAVARIABLE_ClassDecl",
                 "METAVARIABLE_TypeArgument",
                 "METAVARIABLE_LastFormalParameter",
                 "ObjectKind",
                 "ERROR_TOKEN"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}

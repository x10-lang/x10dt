package org.eclipse.imp.x10dt.formatter.parser;

import lpg.runtime.IAst;
import lpg.runtime.IToken;

import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.x10dt.formatter.parser.ast.AbstractASTNodeList;
import org.eclipse.imp.xform.pattern.parser.ASTAdapterBase;

public class ASTAdapter extends ASTAdapterBase implements ILanguageService,
		PatternX10Parsersym {

	public boolean isList(Object astNode) {
		return astNode instanceof AbstractASTNodeList;
	}

	@Override
	public Object[] getChildren(Object astNode) {
		return ((IAst) astNode).getChildren().toArray();
	}

	@Override
	public int getOffset(Object astNode) {
		return ((IAst) astNode).getLeftIToken().getStartOffset();
	}

	@Override
	public int getLength(Object astNode) {
		IAst ast = (IAst) astNode;
		IToken left = ast.getLeftIToken();
		IToken right = ast.getRightIToken();

		// special case for epsilon trees
		if (left.getTokenIndex() > right.getTokenIndex()) {
			return 0;
		} else {
			int start = left.getStartOffset();
			int end = right.getEndOffset();
			return end - start + 1;
		}
	}

	@Override
	public String getTypeOf(Object astNode) {
		return astNode.getClass().getName();
	}

	@Override
	public boolean isMetaVariable(Object astNode) {
		IAst ast = (IAst) astNode;
		if (ast.getChildren().size() == 0) {
			int k = ast.getLeftIToken().getKind();

			switch (k) {
			case TK_METAVARIABLE_PackageName:
			case TK_METAVARIABLE_X10ClassModifier:
			case TK_METAVARIABLE_X10ClassModifiers:
			case TK_METAVARIABLE_Property:
			case TK_METAVARIABLE_Properties:
			case TK_METAVARIABLE_identifier:
			case TK_METAVARIABLE_Type:
			case TK_METAVARIABLE_ConstExpression:
			case TK_METAVARIABLE_DepParameterExp:
			case TK_METAVARIABLE_MethodModifier:
			case TK_METAVARIABLE_Statement:
			case TK_METAVARIABLE_ClockList:
			case TK_METAVARIABLE_Expression:
			case TK_METAVARIABLE_FieldModifier:
			case TK_METAVARIABLE_ArgumentList:
			case TK_METAVARIABLE_Object:
			case TK_METAVARIABLE_TypeNode:
			case TK_METAVARIABLE_PackageNode:
			case TK_METAVARIABLE_Import:
			case TK_METAVARIABLE_ClassDecl:
			case TK_METAVARIABLE_ClassBodyDeclaration:
			case TK_METAVARIABLE_ClassBodyDeclarations:
			case TK_METAVARIABLE_TypeDeclaration:
			case TK_METAVARIABLE_TypeDeclarations:
			case TK_METAVARIABLE_FieldModifiers:
			case TK_METAVARIABLE_BlockStatement:
			case TK_METAVARIABLE_BlockStatements:
				return true;
			default:
				return false;
			}
		}

		return false;
	}

}

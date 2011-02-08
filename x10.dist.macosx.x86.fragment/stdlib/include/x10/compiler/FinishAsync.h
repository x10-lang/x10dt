#ifndef __X10_COMPILER_FINISHASYNC_H
#define __X10_COMPILER_FINISHASYNC_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANNOTATIONS_STATEMENTANNOTATION_H_NODEPS
#include <x10/lang/annotations/StatementAnnotation.h>
#undef X10_LANG_ANNOTATIONS_STATEMENTANNOTATION_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 

class FinishAsync   {
    public:
    RTT_H_DECLS_INTERFACE
    
    x10_int FMGL(arity);
    
    x10_int FMGL(place);
    
    x10_boolean FMGL(isLast);
    
    x10_int FMGL(pattern);
    
    template <class I> struct itable {
        itable(x10_int (I::*arity) (), x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10_boolean (I::*isLast) (), x10_int (I::*pattern) (), x10_int (I::*place) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : arity(arity), equals(equals), hashCode(hashCode), isLast(isLast), pattern(pattern), place(place), toString(toString), typeName(typeName) {}
        x10_int (I::*arity) ();
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10_boolean (I::*isLast) ();
        x10_int (I::*pattern) ();
        x10_int (I::*place) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_int arity(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->arity))();
    }
    template <class R> static x10_int arity(R _recv) {
        return R::_METHODS::arity(_recv);
    }
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10_boolean isLast(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->isLast))();
    }
    template <class R> static x10_boolean isLast(R _recv) {
        return R::_METHODS::isLast(_recv);
    }
    template <class R> static x10_int pattern(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->pattern))();
    }
    template <class R> static x10_int pattern(R _recv) {
        return R::_METHODS::pattern(_recv);
    }
    template <class R> static x10_int place(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->place))();
    }
    template <class R> static x10_int place(R _recv) {
        return R::_METHODS::place(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::FinishAsync>(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};

} } 
#endif // X10_COMPILER_FINISHASYNC_H

namespace x10 { namespace compiler { 
class FinishAsync;
} } 

#ifndef X10_COMPILER_FINISHASYNC_H_NODEPS
#define X10_COMPILER_FINISHASYNC_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/annotations/StatementAnnotation.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#ifndef X10_COMPILER_FINISHASYNC_H_GENERICS
#define X10_COMPILER_FINISHASYNC_H_GENERICS
#endif // X10_COMPILER_FINISHASYNC_H_GENERICS
#endif // __X10_COMPILER_FINISHASYNC_H_NODEPS

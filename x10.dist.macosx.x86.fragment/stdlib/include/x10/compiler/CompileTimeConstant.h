#ifndef __X10_COMPILER_COMPILETIMECONSTANT_H
#define __X10_COMPILER_COMPILETIMECONSTANT_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANNOTATIONS_METHODANNOTATION_H_NODEPS
#include <x10/lang/annotations/MethodAnnotation.h>
#undef X10_LANG_ANNOTATIONS_METHODANNOTATION_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace compiler { 

class CompileTimeConstant   {
    public:
    RTT_H_DECLS_INTERFACE
    
    x10aux::ref<x10::lang::String> FMGL(option);
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10aux::ref<x10::lang::String> (I::*option) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hashCode(hashCode), option(option), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10aux::ref<x10::lang::String> (I::*option) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::CompileTimeConstant>(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::CompileTimeConstant>(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> option(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::CompileTimeConstant>(_refRecv->_getITables())->option))();
    }
    template <class R> static x10aux::ref<x10::lang::String> option(R _recv) {
        return R::_METHODS::option(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::CompileTimeConstant>(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::CompileTimeConstant>(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};

} } 
#endif // X10_COMPILER_COMPILETIMECONSTANT_H

namespace x10 { namespace compiler { 
class CompileTimeConstant;
} } 

#ifndef X10_COMPILER_COMPILETIMECONSTANT_H_NODEPS
#define X10_COMPILER_COMPILETIMECONSTANT_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/annotations/MethodAnnotation.h>
#include <x10/lang/String.h>
#ifndef X10_COMPILER_COMPILETIMECONSTANT_H_GENERICS
#define X10_COMPILER_COMPILETIMECONSTANT_H_GENERICS
#endif // X10_COMPILER_COMPILETIMECONSTANT_H_GENERICS
#endif // __X10_COMPILER_COMPILETIMECONSTANT_H_NODEPS

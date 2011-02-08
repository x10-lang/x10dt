#ifndef __X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H
#define __X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANNOTATIONS_CLASSANNOTATION_H_NODEPS
#include <x10/lang/annotations/ClassAnnotation.h>
#undef X10_LANG_ANNOTATIONS_CLASSANNOTATION_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace compiler { 

class NativeCPPCompilationUnit   {
    public:
    RTT_H_DECLS_INTERFACE
    
    x10aux::ref<x10::lang::String> FMGL(unit);
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) (), x10aux::ref<x10::lang::String> (I::*unit) ()) : equals(equals), hashCode(hashCode), toString(toString), typeName(typeName), unit(unit) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
        x10aux::ref<x10::lang::String> (I::*unit) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::NativeCPPCompilationUnit>(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::NativeCPPCompilationUnit>(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::NativeCPPCompilationUnit>(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::NativeCPPCompilationUnit>(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> unit(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::NativeCPPCompilationUnit>(_refRecv->_getITables())->unit))();
    }
    template <class R> static x10aux::ref<x10::lang::String> unit(R _recv) {
        return R::_METHODS::unit(_recv);
    }
    void _instance_init();
    
    
};

} } 
#endif // X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H

namespace x10 { namespace compiler { 
class NativeCPPCompilationUnit;
} } 

#ifndef X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H_NODEPS
#define X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/annotations/ClassAnnotation.h>
#include <x10/lang/String.h>
#ifndef X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H_GENERICS
#define X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H_GENERICS
#endif // X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H_GENERICS
#endif // __X10_COMPILER_NATIVECPPCOMPILATIONUNIT_H_NODEPS

#ifndef __X10_COMPILER_REF_H
#define __X10_COMPILER_REF_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
#define X10_LANG_ANNOTATIONS_STATEMENTANNOTATION_H_NODEPS
#include <x10/lang/annotations/StatementAnnotation.h>
#undef X10_LANG_ANNOTATIONS_STATEMENTANNOTATION_H_NODEPS
namespace x10 { namespace compiler { 

template<class FMGL(T)> class Ref;
template <> class Ref<void>;
template<class FMGL(T)> class Ref   {
    public:
    RTT_H_DECLS_INTERFACE
    
    FMGL(T) FMGL(id);
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), FMGL(T) (I::*id) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hashCode(hashCode), id(id), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        FMGL(T) (I::*id) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Ref<FMGL(T)> >(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Ref<FMGL(T)> >(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static FMGL(T) id(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Ref<FMGL(T)> >(_refRecv->_getITables())->id))();
    }
    template <class R> static FMGL(T) id(R _recv) {
        return R::_METHODS::id(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Ref<FMGL(T)> >(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Ref<FMGL(T)> >(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    
};
template <> class Ref<void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_COMPILER_REF_H

namespace x10 { namespace compiler { 
template<class FMGL(T)> class Ref;
} } 

#ifndef X10_COMPILER_REF_H_NODEPS
#define X10_COMPILER_REF_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/annotations/StatementAnnotation.h>
#ifndef X10_COMPILER_REF_H_GENERICS
#define X10_COMPILER_REF_H_GENERICS
#endif // X10_COMPILER_REF_H_GENERICS
#ifndef X10_COMPILER_REF_H_IMPLEMENTATION
#define X10_COMPILER_REF_H_IMPLEMENTATION
#include <x10/compiler/Ref.h>



//#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/compiler/Ref.x10": x10.ast.PropertyDecl_c
template<class FMGL(T)> void x10::compiler::Ref<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::compiler::Ref<FMGL(T)>");
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::compiler::Ref<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::compiler::Ref<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::compiler::Ref<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::lang::annotations::StatementAnnotation>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.compiler.Ref";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::interface_kind, 2, parents, 1, params, variances);
}
#endif // X10_COMPILER_REF_H_IMPLEMENTATION
#endif // __X10_COMPILER_REF_H_NODEPS

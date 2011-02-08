#ifndef __X10_COMPILER_PRAGMA_H
#define __X10_COMPILER_PRAGMA_H

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
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { 

class Pragma   {
    public:
    RTT_H_DECLS_INTERFACE
    
    x10_int FMGL(pragma);
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10_int (I::*pragma) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) ()) : equals(equals), hashCode(hashCode), pragma(pragma), toString(toString), typeName(typeName) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10_int (I::*pragma) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Pragma>(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Pragma>(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10_int pragma(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Pragma>(_refRecv->_getITables())->pragma))();
    }
    template <class R> static x10_int pragma(R _recv) {
        return R::_METHODS::pragma(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Pragma>(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::compiler::Pragma>(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    void _instance_init();
    
    static x10_int FMGL(FINISH_ASYNC);
    
    static inline x10_int FMGL(FINISH_ASYNC__get)() {
        return x10::compiler::Pragma::FMGL(FINISH_ASYNC);
    }
    static x10_int FMGL(FINISH_HERE);
    
    static inline x10_int FMGL(FINISH_HERE__get)() {
        return x10::compiler::Pragma::FMGL(FINISH_HERE);
    }
    static x10_int FMGL(FINISH_SPMD);
    
    static inline x10_int FMGL(FINISH_SPMD__get)() {
        return x10::compiler::Pragma::FMGL(FINISH_SPMD);
    }
    static x10_int FMGL(FINISH_LOCAL);
    
    static inline x10_int FMGL(FINISH_LOCAL__get)() {
        return x10::compiler::Pragma::FMGL(FINISH_LOCAL);
    }
    static x10_int FMGL(FINISH_ASYNC_AND_BACK);
    
    static void FMGL(FINISH_ASYNC_AND_BACK__do_init)();
    static void FMGL(FINISH_ASYNC_AND_BACK__init)();
    static volatile x10aux::status FMGL(FINISH_ASYNC_AND_BACK__status);
    static inline x10_int FMGL(FINISH_ASYNC_AND_BACK__get)() {
        if (FMGL(FINISH_ASYNC_AND_BACK__status) != x10aux::INITIALIZED) {
            FMGL(FINISH_ASYNC_AND_BACK__init)();
        }
        return x10::compiler::Pragma::FMGL(FINISH_ASYNC_AND_BACK);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(FINISH_ASYNC_AND_BACK__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(FINISH_ASYNC_AND_BACK__id);
    
    static x10_int FMGL(FINISH_ATEACH_UNIQUE);
    
    static void FMGL(FINISH_ATEACH_UNIQUE__do_init)();
    static void FMGL(FINISH_ATEACH_UNIQUE__init)();
    static volatile x10aux::status FMGL(FINISH_ATEACH_UNIQUE__status);
    static inline x10_int FMGL(FINISH_ATEACH_UNIQUE__get)() {
        if (FMGL(FINISH_ATEACH_UNIQUE__status) != x10aux::INITIALIZED) {
            FMGL(FINISH_ATEACH_UNIQUE__init)();
        }
        return x10::compiler::Pragma::FMGL(FINISH_ATEACH_UNIQUE);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(FINISH_ATEACH_UNIQUE__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(FINISH_ATEACH_UNIQUE__id);
    
    
};

} } 
#endif // X10_COMPILER_PRAGMA_H

namespace x10 { namespace compiler { 
class Pragma;
} } 

#ifndef X10_COMPILER_PRAGMA_H_NODEPS
#define X10_COMPILER_PRAGMA_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/annotations/StatementAnnotation.h>
#include <x10/lang/Int.h>
#ifndef X10_COMPILER_PRAGMA_H_GENERICS
#define X10_COMPILER_PRAGMA_H_GENERICS
#endif // X10_COMPILER_PRAGMA_H_GENERICS
#endif // __X10_COMPILER_PRAGMA_H_NODEPS

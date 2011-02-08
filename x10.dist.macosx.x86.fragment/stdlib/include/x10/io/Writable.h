#ifndef __X10_IO_WRITABLE_H
#define __X10_IO_WRITABLE_H

#include <x10rt.h>


#define X10_LANG_ANY_H_NODEPS
#include <x10/lang/Any.h>
#undef X10_LANG_ANY_H_NODEPS
namespace x10 { namespace io { 
class Writer;
} } 
namespace x10 { namespace io { 

class Writable   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>), x10_int (I::*hashCode) (), x10aux::ref<x10::lang::String> (I::*toString) (), x10aux::ref<x10::lang::String> (I::*typeName) (), void (I::*write) (x10aux::ref<x10::io::Writer>)) : equals(equals), hashCode(hashCode), toString(toString), typeName(typeName), write(write) {}
        x10_boolean (I::*equals) (x10aux::ref<x10::lang::Any>);
        x10_int (I::*hashCode) ();
        x10aux::ref<x10::lang::String> (I::*toString) ();
        x10aux::ref<x10::lang::String> (I::*typeName) ();
        void (I::*write) (x10aux::ref<x10::io::Writer>);
    };
    
    template <class R> static x10_boolean equals(x10aux::ref<R> _recv, x10aux::ref<x10::lang::Any> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::io::Writable>(_refRecv->_getITables())->equals))(arg0);
    }
    template <class R> static x10_boolean equals(R _recv, x10aux::ref<x10::lang::Any> arg0) {
        return R::_METHODS::equals(_recv, arg0);
    }
    template <class R> static x10_int hashCode(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::io::Writable>(_refRecv->_getITables())->hashCode))();
    }
    template <class R> static x10_int hashCode(R _recv) {
        return R::_METHODS::hashCode(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::io::Writable>(_refRecv->_getITables())->toString))();
    }
    template <class R> static x10aux::ref<x10::lang::String> toString(R _recv) {
        return R::_METHODS::toString(_recv);
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(x10aux::ref<R> _recv) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        return (_refRecv.operator->()->*(x10aux::findITable<x10::io::Writable>(_refRecv->_getITables())->typeName))();
    }
    template <class R> static x10aux::ref<x10::lang::String> typeName(R _recv) {
        return R::_METHODS::typeName(_recv);
    }
    template <class R> static void write(x10aux::ref<R> _recv, x10aux::ref<x10::io::Writer> arg0) {
        x10aux::ref<x10::lang::Reference> _refRecv(_recv);
        (_refRecv.operator->()->*(x10aux::findITable<x10::io::Writable>(_refRecv->_getITables())->write))(arg0);
    }
    template <class R> static void write(R _recv, x10aux::ref<x10::io::Writer> arg0) {
        R::_METHODS::write(_recv, arg0);
    }
    void _instance_init();
    
    
};

} } 
#endif // X10_IO_WRITABLE_H

namespace x10 { namespace io { 
class Writable;
} } 

#ifndef X10_IO_WRITABLE_H_NODEPS
#define X10_IO_WRITABLE_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/io/Writer.h>
#ifndef X10_IO_WRITABLE_H_GENERICS
#define X10_IO_WRITABLE_H_GENERICS
#endif // X10_IO_WRITABLE_H_GENERICS
#endif // __X10_IO_WRITABLE_H_NODEPS

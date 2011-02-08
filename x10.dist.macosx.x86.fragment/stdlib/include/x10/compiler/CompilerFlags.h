#ifndef __X10_COMPILER_COMPILERFLAGS_H
#define __X10_COMPILER_COMPILERFLAGS_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 
class Native;
} } 
namespace x10 { namespace compiler { 
class CompileTimeConstant;
} } 
namespace x10 { namespace compiler { 

class CompilerFlags : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual x10aux::ref<x10::compiler::CompilerFlags> x10__compiler__CompilerFlags____x10__compiler__CompilerFlags__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::compiler::CompilerFlags> _make();
    
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_COMPILER_COMPILERFLAGS_H

namespace x10 { namespace compiler { 
class CompilerFlags;
} } 

#ifndef X10_COMPILER_COMPILERFLAGS_H_NODEPS
#define X10_COMPILER_COMPILERFLAGS_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Boolean.h>
#include <x10/compiler/Native.h>
#include <x10/compiler/CompileTimeConstant.h>
#ifndef X10_COMPILER_COMPILERFLAGS_H_GENERICS
#define X10_COMPILER_COMPILERFLAGS_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::CompilerFlags::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::CompilerFlags> this_ = new (memset(x10aux::alloc<x10::compiler::CompilerFlags>(), 0, sizeof(x10::compiler::CompilerFlags))) x10::compiler::CompilerFlags();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_COMPILERFLAGS_H_GENERICS
#endif // __X10_COMPILER_COMPILERFLAGS_H_NODEPS
